# MilitaryBot

## Dependencies
Requires [FRC Driver Station](http://www.ni.com/download/first-robotics-software-2017/7183/en/)

## Installation
Git clone this repository in your directory of choice.
Default location is $HOME/git/

## Setup
1. Power on glove and connect to it via Bluetooth (ID PGMkIM, PIN 1234)
2. Connect to robot radio WiFi network (SSID 4188_Turtle)
3. Open FRC Driver Station software and ensure that all boxes are green.
4. Navigate to $INSTALLATION_DIR/MilitaryBot/NTClient using shell of choice 
    * In Windows Powershell / bash, type "cd ~/git/MilitaryBot/NTClient" if installed in default location.
5. Run "./gradlew run" to start client program.
6. Type in com port that glove is connected to, e.g. "COM3" or "COM5"
    * Found in Windows device manager under Ports (LPT & COM).
    * Use first one called "Standard serial over bluetooth"
7. Press enable on FRC Driver Station
    * Bot will be driven by joystick by default
    * To switch to Glove control, press A on controller
    
## About this project
This repository stores all the code necessary to run 4188's prototype Military Robot using our custom made "Power Glove."
* Power Glove and its Arduino code courtesy of @CarterTheGreat
* Client side and robot code by @LockeAdams
