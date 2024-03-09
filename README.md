# DungeonGame

UNSW COMP2511 Project
team work by two people  
  
## Overview
**This is a dungeon-style game designed for desktop, that allows the user to move the player character to interact with other entities in a variety of dungeon environments. We followed an agile development process to design and implement this application.**  
  
**This application reads input from JSON files and UI component uses JavaFX.**

## Dungeon Layout
  
<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/layout.png" width="800"/>  
  
## Domain modelling
**UML Class Diagram**
  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UMLdiagram.png)  
  
## Backend Implementation
Player's movement is controlled by keyboard input from the user. The player interacts with different entities.  

<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/entity_player.png" width="500"/>  

Enemies move towards the player unless blocked by wall, move away from player when the player is invincible.  (Extension: AI movements?)  

<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/entity_enemymove.png" width="500"/>   

Basic goals include *getting to an exit, destroying allenemies, having a boulder on all floor switched, collecting all treasures.* To complete the dungeon, the goal can be can of these 4 or a combination of them.  

<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/sc_goalcondition.png" width="500"/>   

Entities like *Door, Key,* and *Portal* are in pairs, each pair is assigned with the same ID.  
  
<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/entity_door.png" width="500"/>  
  
<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/entity_key.png" width="500"/>    
  
<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/entity_portal.png" width="500"/>    

*Observer Pattern* is used to track the position of entities and update status of each entity.  

<img src="https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/sc_observer.png" width="500"/>    
  

## UI design

  GUI is implemented in FXML, with selection of different maps.  
  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_bs.png)  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_bs2.png)  

  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_enemies.png)  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_enemies2.png)  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_enemies3.png)  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_enemies4.png)  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_enemies5.png)  

  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_treasure.png)  
![Image text](https://github.com/RebeccaSY/DungeonGame/blob/main/Demo/UI_treasure2.png)  


