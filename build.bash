#!/bin/bash
set -e -v
javac src/Game.java src/Model.java src/View.java src/Controller.java src/Sprite.java src/Bird.java src/Tree.java src/Egg.java src/AmmoBar.java src/Plant.java

mv src/*.class .
