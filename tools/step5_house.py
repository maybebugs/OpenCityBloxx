#!/usr/bin/env python3
"""
step5_house.py - Precision de-obfuscation for House.java public methods and callers in CityMode.java
"""

import os
import re
import shutil

SRC_DIR = "src"

def backup(filepath):
    bak = filepath + ".bak"
    if not os.path.exists(bak):
        shutil.copyfile(filepath, bak)

def update_file(filepath, transforms):
    backup(filepath)
    with open(filepath, "r", encoding="utf-8") as f:
        content = f.read()

    orig = content
    for pattern, replacement in transforms:
        content = re.sub(pattern, replacement, content)

    if content != orig:
        with open(filepath, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"[MODIFIED] {filepath}")
    else:
        print(f"[UNCHANGED] {filepath}")

def run():
    # 1. Update CityMode.java
    cm_transforms = [
        # wrapText
        (r'\bHouse\.a\((.*?),\s*ac,\s*i\)', r'House.wrapText(\1, ac, i)'),
        # showPrompt
        (r'\bHouse\.a\((.*?),\s*null,\s*null\)', r'House.showPrompt(\1, null, null)'),
        (r'\bHouse\.a\((.*?),\s*null,\s*(c\[[^\]]+\])\)', r'House.showPrompt(\1, null, \2)'),
        # paintTutorialModal
        (r'\bHouse\.c\(graphics\)', 'House.paintTutorialModal(graphics)'),
        # getGameFont
        (r'\bHouse\.p\(\)', 'House.getGameFont()'),
        # updateLoadingProgress
        (r'\bHouse\.e\((\d+)\)', r'House.updateLoadingProgress(\1)'),
        # random
        (r'\bHouse\.i\(([^)]+)\)', r'House.random(\1)'),
        # handleModalAction
        (r'\bHouse\.l\(([^)]+)\)', r'House.handleModalAction(\1)'),
    ]
    update_file(os.path.join(SRC_DIR, "CityMode.java"), cm_transforms)

    # 2. Update House.java declarations and internal calls
    house_transforms = [
        # Declarations:
        (r'public static Font p\(\)\s*\{', 'public static Font getGameFont() {'),
        (r'public static String\[\] a\(String text,\s*Font font,\s*int maxWidth\)\s*\{',
         'public static String[] wrapText(String text, Font font, int maxWidth) {'),
        (r'public static void a\(String str,\s*String\[\] strArr,\s*Image image\)\s*\{',
         'public static void showPrompt(String str, String[] strArr, Image image) {'),
        (r'public static void c\(Graphics graphics\)\s*\{', 'public static void paintTutorialModal(Graphics graphics) {'),
        (r'public static boolean e\(int i\)\s*\{', 'public static boolean updateLoadingProgress(int i) {'),
        (r'public static int i\(int i\)\s*\{', 'public static int random(int i) {'),
        (r'public static boolean l\(int i\)\s*\{', 'public static boolean handleModalAction(int i) {'),

        # Internal calls in House.java:
        (r'\bHouse\.p\(\)', 'House.getGameFont()'),
        (r'\bHouse\.e\((\d+)\)', r'House.updateLoadingProgress(\1)'),
        (r'\bHouse\.i\(([^)]+)\)', r'House.random(\1)'),
        (r'\bHouse\.c\(graphics\)', 'House.paintTutorialModal(graphics)'),
        (r'\bHouse\.a\((.*?),\s*null,\s*null\)', r'House.showPrompt(\1, null, null)'),
        (r'\bHouse\.l\(([^)]+)\)', r'House.handleModalAction(\1)'),
    ]
    update_file(os.path.join(SRC_DIR, "House.java"), house_transforms)

if __name__ == "__main__":
    run()
