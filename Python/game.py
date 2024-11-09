import pygame
import sys
import random 
#import time


# Initialize PyGame
pygame.init()

# Screen Setup
screen = pygame.display.set_mode((800, 600))
pygame.display.set_caption('Sustainable Society Game')
font = pygame.font.Font(None, 30)

# Resource dictionary to hold current resources
resources = {"food": int(100), "water": int(100), "energy": int(100), "material": int(100)}

# Colors for UI elements
WHITE = (255, 255, 255)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
RED = (255, 0, 0)
YELLOW = (255, 255, 0)
DARK_GREY = (50, 50, 50)

# Load building images
building_images = {
    "Farm": pygame.transform.scale(pygame.image.load("farm.png"), (50,50)),
    "Water Plant": pygame.transform.scale(pygame.image.load("water_plant.png"), (50,50)),
    "Power Plant": pygame.transform.scale(pygame.image.load("power_plant.png"), (50,50)),
    "Nuclear Power Plant": pygame.transform.scale(pygame.image.load("nuclear_power_plant.png"), (50,50)),
    "Apartment": pygame.transform.scale(pygame.image.load("apartment.jpg"), (50,50)),
}

# Location and Building setup
class Location:
    def __init__(self, x, y):
        self.rect = pygame.Rect(x, y, 50, 50)
        self.building = None
        self.is_building_menu_open = False
        self.is_upgrade_menu_open = False
        self.building_type = None

    def open_build_menu(self):
        self.is_building_menu_open = True

    def open_upgrade_menu(self):
        self.is_upgrade_menu_open = True

    def build(self, building_type):
        self.building = Building(building_type)
        self.building_type = building_type
        self.is_building_menu_open = False  # Close the build menu once built

    def upgrade(self):
        if self.building and self.building.level < 3:
            self.building.upgrade()

class Building:
    def __init__(self, building_type):
        self.type = building_type
        self.level = 1
        self.image = building_images.get(building_type)

        #what materials are used every loop
        self.resource_usage = {"food": 0, "water": 0, "energy": 0, "material": 0}

        #what materials are generated every loop
        self.resource_generation = {"food": 0, "water": 0, "energy": 0, "material": 0}

        #Rule: every building produces 10 of one resource. 
        #There is a main resource used (by 4 units), and supplemental resources that are used (by 1 unit)
        #generation to usage ratio is 10:6
        if building_type == "Farm":
            self.resource_usage = {"food": 0, "water": 4, "energy": 1, "material": 1}
            self.resource_generation = {"food": 10, "water": 0, "energy": 0, "material": 0}
        elif building_type == "Water Plant":
            self.resource_usage = {"food": 1, "water": 0, "energy": 4, "material": 1}
            self.resource_generation = {"food": 0, "water": 10, "energy": 0, "material": 0}
        elif building_type == "Nuclear Power Plant":
            self.resource_usage = {"food": 1, "water": 1, "energy": 0, "material": 4}
            self.resource_generation = {"food": 0, "water": 0, "energy": 10, "material": 0}
        elif building_type == "Apartment":
            self.resource_usage = {"food": 4, "water": 1, "energy": 1, "material": 0}
            self.resource_generation = {"food": 0, "water": 0, "energy": 0, "material": 10}

    def upgrade(self):
        self.level += 1
        if self.level > 3:
            self.level = 3
        self.resource_usage = {key: value * self.level for key, value in self.resource_usage.items()}
        self.resource_generation = {key: value * self.level for key, value in self.resource_generation.items()}

    # def get_color(self):
    #     if self.type == "Farm":
    #         return GREEN
    #     elif self.type == "Water Plant":
    #         return BLUE
    #     elif self.type == "Power Plant":
    #         return YELLOW
    #     else:
    #         return WHITE

    def draw_level(self, screen, x, y):
        level_text = font.render(f"Lvl {self.level}", True, WHITE)
        screen.blit(level_text, (x + 15, y + 15))

# Camera control with limits
camera_x, camera_y = 0, 0
map_width, map_height = 1600, 1200  # Dimensions of the map
camera_limit_x = 0  # Set left limit
camera_limit_y = 0  # Set top limit

# Camera movement limits (camera cannot go beyond the map's borders)
# def handle_camera_movement():
#     global camera_x, camera_y
#     keys = pygame.key.get_pressed()
#     if keys[pygame.K_RIGHT]:
#         if camera_x > -map_width + 800:  # Limit right
#             camera_x -= 5
#     if keys[pygame.K_LEFT]:
#         if camera_x < 0:  # Limit left
#             camera_x += 5
#     if keys[pygame.K_UP]:
#         if camera_y < 0:  # Limit up
#             camera_y += 5
#     if keys[pygame.K_DOWN]:
#         if camera_y > -map_height + 600:  # Limit down
#             camera_y -= 5

# List of locations (these are clickable areas where the player can build)
locations = [Location(x, y) for x in range(100, 1600, 200) for y in range(100, 1200, 200)]

# Draw Resource UI
def draw_resources():
    resources_text = f"Food: {resources['food']}   Water: {resources['water']}   Energy: {resources['energy']}   Material: {resources['material']}"
    text_surface = font.render(resources_text, True, WHITE)
    screen.blit(text_surface, (10, 10))

# Draw Controls UI
def draw_controls():
    controls_text = [
        "Controls:",
        #"Arrow keys: Move Camera",
        "Click on Locations: Open Build Menu",
        "Press B: Build Structure",
        "Press U: Upgrade Structure"
    ]
    
    y_offset = 50
    for line in controls_text:
        control_surface = font.render(line, True, WHITE)
        screen.blit(control_surface, (10, y_offset))
        y_offset += 30

# Draw Build Menu
def draw_build_menu():
    build_menu_text = "Build Menu:"
    options = ["1: Farm", "2: Water Plant", "3: Power Plant", "4: Apartment"]
    screen.blit(font.render(build_menu_text, True, WHITE), (10, 200))
    y_offset = 240
    for option in options:
        screen.blit(font.render(option, True, WHITE), (10, y_offset))
        y_offset += 30

# Main game loop
def game_loop(): 
    global camera_x, camera_y
    counter = 0
    selected_building = None
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.MOUSEBUTTONDOWN:
                mouse_x, mouse_y = pygame.mouse.get_pos()
                for location in locations:
                    if location.rect.collidepoint(mouse_x - camera_x, mouse_y - camera_y):
                        if location.building:
                            location.open_upgrade_menu()
                        else:
                            location.open_build_menu()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_b and selected_building:
                    # Example: Build the selected building
                    for location in locations:
                        if location.is_building_menu_open:
                            location.build(selected_building)
                elif event.key == pygame.K_u:
                    for location in locations:
                        if location.is_upgrade_menu_open:
                            location.upgrade()
                            location.is_upgrade_menu_open = False
                elif event.key == pygame.K_1:
                    selected_building = "Farm"
                elif event.key == pygame.K_2:
                    selected_building = "Water Plant"
                elif event.key == pygame.K_3:
                    selected_building = "Power Plant"
                elif event.key == pygame.K_4:
                    selected_building = "Apartment"

        # handle_camera_movement()

        if (counter >= 2000):
            # Resource generation/consumption for each building
            for location in locations:
                if location.building:
                    for resource in resources:
                        resources[resource] += location.building.resource_generation.get(resource, 0)
                        resources[resource] -= location.building.resource_usage.get(resource, 0)
            counter = 0
        else:
            counter = counter + 1

        # Check for loss condition
        if all(resource <= 0 for resource in resources.values()):
            print("Game Over: Resources are all zero.")
            pygame.quit()
            sys.exit()

        # Fill the screen with background color
        screen.fill(DARK_GREY)
        
        # Draw the map (locations and buildings)
        for location in locations:
            pygame.draw.rect(screen, GREEN, location.rect.move(camera_x, camera_y))
            if location.building:
                # Draw a visual representation of the building
                screen.blit(location.building.image, location.rect)
                location.building.draw_level(screen, location.rect.x, location.rect.y)

            # Draw menus for build and upgrade
            if location.is_building_menu_open:
                draw_build_menu()
            if location.is_upgrade_menu_open:
                menu_text = f"Upgrade {location.building.type} (Level {location.building.level})"
                screen.blit(font.render(menu_text, True, WHITE), (10, 500))

        # Draw UI elements (Resources and Controls)
        draw_resources()
        draw_controls()

        # Update the screen
        pygame.display.flip()

# Run the game loop
game_loop()
