#!/usr/bin/env python3
"""
deobfuscate.py - Precision de-obfuscation script for OpenCityBloxx (City Bloxx).
Renames single-letter obfuscated methods to clean, authentic descriptive names.
Creates backups (.bak) of all modified files.
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

def step1_citymode_callers():
    """Update callers of CityMode in House.java and GameCanvas.java."""
    print("\n--- Step 1: Updating CityMode callers in House.java and GameCanvas.java ---")
    house_transforms = [
        (r'\bCityMode\.i\(\)', 'CityMode.isCheatActive()'),
        (r'\bCityMode\.a\(bl,\s*bt,\s*bq\)', 'CityMode.startPlacement(bl, bt, bq)'),
        (r'\bCityMode\.a\(\)', 'CityMode.init()'),
        (r'\bCityMode\.b\(\)', 'CityMode.enterCity()'),
        (r'\bCityMode\.c\(\)', 'CityMode.loadAssets()'),
        (r'\bCityMode\.d\(\)', 'CityMode.getBuildingTowerType()'),
        (r'\bCityMode\.l\(\)', 'CityMode.unloadAssets()'),
        (r'\bCityMode\.j\(\)', 'CityMode.consumeHighScoreFlag()'),
        (r'\bCityMode\.f\(\)', 'CityMode.isCityModeActive()'),
        (r'\bCityMode\.a\(i,\s*i2\)', 'CityMode.handleKeyPressed(i, i2)'),
        (r'\bCityMode\.e\(\)', 'CityMode.clearInputState()'),
        (r'\bCityMode\.g\(\)', 'CityMode.updateUnlocks()'),
        (r'\bCityMode\.h\(\)', 'CityMode.recalculateSynergies()'),
    ]
    update_file(os.path.join(SRC_DIR, "House.java"), house_transforms)

def step2_highscores():
    """Update HighScores.java and its callers."""
    print("\n--- Step 2: Updating HighScores ---")
    hs_transforms = [
        (r'public final int a\(int i,\s*int\[\] iArr,\s*String str\)', 'public final int submitScore(int i, int[] iArr, String str)'),
        (r'private void a\(Graphics graphics\)', 'private void paintScoreTable(Graphics graphics)'),
        (r'private static void a\(Graphics graphics,\s*int i,\s*boolean withArrow\)', 'private static void paintArrow(Graphics graphics, int i, boolean withArrow)'),
        (r'private void a\(int\[\] iArr,\s*String str\)', 'private void insertScore(int[] iArr, String str)'),
        (r'private void b\(\)\s*\{', 'private void installSoftkeys() {'),
        (r'private void c\(\)\s*\{', 'private void submitNameAndSave() {'),
        (r'private void d\(\)\s*\{', 'private void cancelEntry() {'),
        # Internal calls:
        (r'\bHighScores\.a\(graphics,\s*i,\s*false\)', 'HighScores.paintArrow(graphics, i, false)'),
        (r'\bHighScores\.a\(graphics,\s*42,\s*true\)', 'HighScores.paintArrow(graphics, 42, true)'),
        (r'\bHighScores\.paintScoreTable\(graphics,\s*42,\s*true\)', 'HighScores.paintArrow(graphics, 42, true)'),
        (r'\bHighScores\.paintScoreTable\(graphics,\s*i,\s*false\)', 'HighScores.paintArrow(graphics, i, false)'),
        (r'\bhVar\.a\(iArr2,\s*str\);', 'hVar.insertScore(iArr2, str);'),
        (r'\bUi\.paintScoreTable\(graphics\);', 'Ui.paint(graphics);'),
        (r'(?<!Ui\.)\ba\(graphics\);', 'paintScoreTable(graphics);'),
        (r'\ba\(this\.d,\s*this\.c\);', 'insertScore(this.d, this.c);'),
        (r'\bb\(\);', 'installSoftkeys();'),
        (r'\bc\(\);', 'submitNameAndSave();'),
        (r'\bd\(\);', 'cancelEntry();'),
    ]
    update_file(os.path.join(SRC_DIR, "HighScores.java"), hs_transforms)

    # In House.java:
    house_hs = [
        (r'this\.highScores\.a\(1,\s*new int\[\]\{bt,\s*bs\},\s*null\)', 'this.highScores.submitScore(1, new int[]{bt, bs}, null)'),
        (r'this\.highScores\.a\(0,\s*new int\[\]\{CityMode\.getPopulation\(\),\s*0\},\s*"-"\)', 'this.highScores.submitScore(0, new int[]{CityMode.getPopulation(), 0}, "-")'),
        (r'this\.highScores\.a\(1,\s*new int\[\]\{bt,\s*bs\},\s*MenuController\.getPlayerName\(\)\)', 'this.highScores.submitScore(1, new int[]{bt, bs}, MenuController.getPlayerName())'),
        (r'this\.highScores\.a\(1,\s*new int\[\]\{bt,\s*bs\},\s*MenuController\.b\(\)\)', 'this.highScores.submitScore(1, new int[]{bt, bs}, MenuController.getPlayerName())'),
    ]
    update_file(os.path.join(SRC_DIR, "House.java"), house_hs)

def step3_menucontroller():
    """Update MenuController.java and its callers."""
    print("\n--- Step 3: Updating MenuController ---")
    mc_transforms = [
        (r'public static String b\(\)\s*\{', 'public static String getPlayerName() {'),
        (r'public static int\[\] a\(\)\s*\{', 'public static int[] getMenuHierarchy() {'),
    ]
    update_file(os.path.join(SRC_DIR, "MenuController.java"), mc_transforms)

    midlet_mc = [
        (r'Ui\.a\(MenuController\.a\(\)\)', 'Ui.setColorPalette(MenuController.getMenuHierarchy())'),
        (r'Ui\.setColorPalette\(MenuController\.a\(\)\)', 'Ui.setColorPalette(MenuController.getMenuHierarchy())'),
    ]
    update_file(os.path.join(SRC_DIR, "GameMIDlet.java"), midlet_mc)

def step4_ui():
    """Update Ui.java and all callers."""
    print("\n--- Step 4: Updating Ui.java and callers ---")
    ui_transforms = [
        (r'public static void a\(\)\s*\{', 'public static void clearDialog() {'),
        (r'public static void a\(int delta\)\s*\{', 'public static void update(int delta) {'),
        (r'public static void a\(int i,\s*int i2,\s*int i3,\s*int i4,\s*int i5,\s*int i6,\s*int\[\] iArr\)\s*\{',
         'public static void setLayout(int i, int i2, int i3, int i4, int i5, int i6, int[] iArr) {'),
        (r'public static void a\(int i,\s*Image image,\s*String str,\s*int i2\)\s*\{',
         'public static void setMenuItem(int i, Image image, String str, int i2) {'),
        (r'public static void a\(int i,\s*Image image,\s*String str,\s*int i2,\s*int i3,\s*int i4,\s*int i5,\s*int i6,\s*String str2\)\s*\{',
         'public static void initMenuList(int i, Image image, String str, int i2, int i3, int i4, int i5, int i6, String str2) {'),
        (r'public static void a\(String str\)\s*\{', 'public static void setTitle(String str) {'),
        (r'public static void a\(Graphics graphics\)\s*\{', 'public static void paint(Graphics graphics) {'),
        (r'public static void a\(Graphics graphics,\s*int i,\s*int i2,\s*boolean z\)\s*\{',
         'public static void paintArrowIndicator(Graphics graphics, int i, int i2, boolean z) {'),
        (r'public static void a\(Image image,\s*String str,\s*int i,\s*int i2,\s*int i3,\s*int i4,\s*int i5,\s*String str2\)\s*\{',
         'public static void openDialog(Image image, String str, int i, int i2, int i3, int i4, int i5, String str2) {'),
        (r'public static void a\(int\[\] iArr\)\s*\{', 'public static void setColorPalette(int[] iArr) {'),
        (r'public static void b\(Graphics graphics,\s*int i,\s*int i2,\s*boolean z\)\s*\{',
         'public static void paintProgressBar(Graphics graphics, int i, int i2, boolean z) {'),
        (r'public static boolean b\(\)\s*\{', 'public static boolean isDialogActive() {'),
        (r'private static void c\(\)\s*\{', 'private static void dismissDialog() {'),
        (r'private static void c\(Graphics graphics\)\s*\{', 'private static void paintDialog(Graphics graphics) {'),
        (r'private static void d\(Graphics graphics\)\s*\{', 'private static void paintScrollIndicator(Graphics graphics) {'),
        (r'private static void e\(Graphics graphics\)\s*\{', 'private static void paintSelectionCursor(Graphics graphics) {'),

        # Internal calls in Ui.java:
        (r'Ui\.c\(\);', 'Ui.dismissDialog();'),
        (r'Ui\.c\(graphics\);', 'Ui.paintDialog(graphics);'),
        (r'Ui\.d\(graphics\);', 'Ui.paintScrollIndicator(graphics);'),
        (r'Ui\.e\(graphics\);', 'Ui.paintSelectionCursor(graphics);'),
        (r'Ui\.a\(graphics,\s*centerX - 9,\s*top \+ 2,\s*false\);', 'Ui.paintArrowIndicator(graphics, centerX - 9, top + 2, false);'),
        (r'Ui\.b\(graphics,\s*centerX - 9,\s*\(bottom - 18\) - 1,\s*true\);', 'Ui.paintProgressBar(graphics, centerX - 9, (bottom - 18) - 1, true);'),
        (r'Ui\.b\(graphics,\s*centerX - 9,\s*\(bottom - 18\) - 1,\s*false\);', 'Ui.paintProgressBar(graphics, centerX - 9, (bottom - 18) - 1, false);'),
        (r'Ui\.a\(graphics,\s*\(u \+ \(w / 2\)\) - 9,\s*v \+ 8,\s*true\);', 'Ui.paintArrowIndicator(graphics, (u + (w / 2)) - 9, v + 8, true);'),
        (r'Ui\.b\(graphics,\s*\(u \+ \(w / 2\)\) - 9,\s*\(\(v \+ x\) - 18\) - 8,\s*false\);', 'Ui.paintProgressBar(graphics, (u + (w / 2)) - 9, ((v + x) - 18) - 8, false);'),
    ]
    update_file(os.path.join(SRC_DIR, "Ui.java"), ui_transforms)

    # Callers in GameMIDlet:
    midlet_ui = [
        (r'Ui\.a\(0\);', 'Ui.update(0);'),
    ]
    update_file(os.path.join(SRC_DIR, "GameMIDlet.java"), midlet_ui)

    # Callers in HighScores:
    hs_ui = [
        (r'Ui\.b\(graphics,\s*\(GameMIDlet\.screenWidth - 18\) / 2,\s*GameMIDlet\.screenHeight - 36,\s*false\);',
         'Ui.paintProgressBar(graphics, (GameMIDlet.screenWidth - 18) / 2, GameMIDlet.screenHeight - 36, false);'),
        (r'Ui\.a\(graphics,\s*\(GameMIDlet\.screenWidth - 18\) >> 1,\s*i,\s*true\);',
         'Ui.paintArrowIndicator(graphics, (GameMIDlet.screenWidth - 18) >> 1, i, true);'),
        (r'Ui\.a\(null,\s*Resources\.getString\(146\),\s*0,\s*0,\s*0,\s*-1,\s*-1,\s*null\);',
         'Ui.openDialog(null, Resources.getString(146), 0, 0, 0, -1, -1, null);'),
        (r'Ui\.a\(null,\s*Resources\.getString\(151\),\s*0,\s*0,\s*0,\s*-1,\s*-1,\s*""\);',
         'Ui.openDialog(null, Resources.getString(151), 0, 0, 0, -1, -1, "");'),
        (r'Ui\.a\(""\);', 'Ui.setTitle("");'),
        (r'Ui\.a\(graphics\);', 'Ui.paint(graphics);'),
        (r'Ui\.paintScoreTable\(graphics\);', 'Ui.paint(graphics);'),
    ]
    update_file(os.path.join(SRC_DIR, "HighScores.java"), hs_ui)

    # Callers in MenuController:
    mc_ui = [
        (r'Ui\.a\(Storage\.cursor,\s*null,\s*d\[i4 \+ c\[Storage\.cursor\]\[4\]\],\s*0\);',
         'Ui.setMenuItem(Storage.cursor, null, d[i4 + c[Storage.cursor][4]], 0);'),
        (r'Ui\.a\(0\);', 'Ui.update(0);'),
        (r'Ui\.a\(20,\s*0,\s*GameMIDlet\.screenWidth - 40,\s*GameMIDlet\.screenHeight - 0,\s*255,\s*style,\s*new int\[\]\{-1,\s*-1,\s*-1\}\);',
         'Ui.setLayout(20, 0, GameMIDlet.screenWidth - 40, GameMIDlet.screenHeight - 0, 255, style, new int[]{-1, -1, -1});'),
        (r'Ui\.a\(Resources\.getString\(titleId\)\);', 'Ui.setTitle(Resources.getString(titleId));'),
        (r'Ui\.a\(entryCount,\s*icon,\s*title,\s*style,\s*0,\s*0,\s*-1,\s*-1,\s*null\);',
         'Ui.initMenuList(entryCount, icon, title, style, 0, 0, -1, -1, null);'),
        (r'Ui\.a\(f,\s*itemIcon,\s*text,\s*state\);', 'Ui.setMenuItem(f, itemIcon, text, state);'),
        (r'Ui\.a\(icon,\s*title,\s*style,\s*0,\s*0,\s*-1,\s*-1,\s*null\);',
         'Ui.openDialog(icon, title, style, 0, 0, -1, -1, null);'),
        (r'Ui\.a\(text\);', 'Ui.setTitle(text);'),
        (r'Ui\.a\(n\);', 'Ui.update(n);'),
        (r'Ui\.b\(\)', 'Ui.isDialogActive()'),
        (r'Ui\.a\(\);', 'Ui.clearDialog();'),
        (r'Ui\.a\(graphics\);', 'Ui.paint(graphics);'),
    ]
    update_file(os.path.join(SRC_DIR, "MenuController.java"), mc_ui)

def main():
    print("Running Steps 1 through 4...")
    step1_citymode_callers()
    step2_highscores()
    step3_menucontroller()
    step4_ui()
    print("Done!")

if __name__ == "__main__":
    main()
