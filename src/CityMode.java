

import java.io.DataInputStream;
import java.io.DataOutputStream;
import jme.Font;
import jme.Graphics;
import jme.Image;

public final class CityMode {
    private static final int[] skyGradient = new int[]{175, 229, 255, -87, -89, 0};
    private static final int[] bandColors = new int[]{-8556946, -1318431, -3351282, -5399421, -11456464, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -14475756, -11456464, -1318431, -8556946, -15463412};
    private static final int[] buildingColors = new int[]{-12357161, -2024952, -15618292, -2449664, -16044658, -8122095, -16491520, -11520000};
    private static final int[] tierColorsPrimary = new int[]{-12355561, -11506121, -10983088, -10329750};
    private static final int[] tierColorsSecondary = new int[]{-8864728, -8409274, -8084131, -7693196};
    private static final int[] cellPalette = new int[]{-16755552, -6290944, -16738304, -7120384, -12793601, -38586, -13107401, -983296};
    private static boolean keyUp;
    private static boolean keyDown;
    private static boolean keyLeft;
    private static boolean keyRight;
    private static boolean keyFire;
    private static Image populationGauge;
    private static Image statusIcons;
    private static Image hudBarSprite;
    private static Image buildIcon;
    private static Image[] buildingSprites;
    private static Image tileEffectFrames;
    private static Image dustEffectFrames;
    private static Image digitFont;
    private static Image digitSheetA;
    private static Image digitSheetB;
    private static final int[] levelThresholds = new int[]{0, 75, 150, 250, 400, 600, 800, 1000, 1400, 1800, 2200, 3000, 4000, 5000, 6500, 8000, 9500, 11500, 14000, 17000, 19000};
    private static int pieceType;
    private static int pieceValue;
    private static int pieceVariant;
    private static int slideX;
    public static int[] grid = null;
    private static int animTick;
    private static int[] highlightCells;
    private static int hintLinesVisible;
    private static int hintScroll;
    private static int hintScrollTimer;
    private static int placeDelay;
    private static int placeTimer;
    private static boolean cursorActive = true;
    private static int slideY;
    private static int placeAnimTimer;
    private static Font font;
    private static String[][] helpLines;
    private static String[] hintPlaceable;
    private static String[] hintCellTooLow;
    private static String[] hintOffGrid;
    private static String[] hintCustom;
    private static final int[] buildingUnlockLevels = new int[]{0, 3, 6, 10};
    private static final int[] specialUnlockLevels = new int[]{8, 12, 14, 16};
    private static int unlockedBuildingTier;
    private static final int[] bonusUnlockLevels = new int[]{0, 1, 4, 7, 9, 11, 13, 15, 18, 20};
    private static String[] levelNames;
    private static String[] unlockMessages;
    private static String[] buildingNames;
    private static int flashColorIndex;
    private static Image digitSheetCurrent;
    private static int blinkTimer;
    private static boolean targetCellEmpty;
    private static boolean cheatActive = false;
    private static final char[] cheatCode = "626428826".toCharArray();
    private static int cheatIndex;
    private static int populationTarget;
    private static int blinkTimer800;
    private static boolean buildingHintPending;
    private static int highestTierUnlocked;
    public static int[] cellLevels;
    public static Image[] levelBadges;
    public static boolean towerGameActive = false;
    public static int selectedBuilding;
    public static int phase;
    public static int population;
    public static int level;
    public static int cursorCol;
    public static int cursorRow;
    public static int slideTimer;
    public static boolean placementInProgress;
    public static int unlockedSpecialTier;
    public static int unlockedBonusTier;
    static boolean modalActive;
    public static int levelBadgeIndex = -1;
    static boolean needsRepaint;
    public static boolean[] tutorialShown;
    public static int populationFlashTimer;
    public static int populationFlashCount;
    public static int populationPending;
    public static boolean highScorePending;
    public static String[] hintLines;
    public static int launchDelay;
    public static int placementResultTimer;
    static int reservedZ = -1;

    static {
        int[] iArr = new int[]{2, 8, 4, 6, 5};
    }

    public static void init() {
        font = House.getGameFont();
        hintLinesVisible = 51;
        hintLinesVisible -= 2;
        hintLinesVisible /= font.getHeight();
        grid = new int[75];
        cellLevels = new int[25];
        tutorialShown = new boolean[46];
        highlightCells = new int[16];
        buildingNames = new String[]{Resources.getString(83), Resources.getString(84), Resources.getString(85), Resources.getString(86), Resources.getString(87), Resources.getString(88), Resources.getString(89), Resources.getString(90)};
        helpLines = new String[12][];
        int i = GameMIDlet.screenWidth - 20;
        String[] strArr = new String[]{Resources.getString(99), Resources.getString(100), Resources.getString(101), Resources.getString(102)};
        for (int i2 = 0; i2 < 4; i2++) {
            helpLines[i2] = House.wrapText(strArr[i2], font, i);
            helpLines[i2 + 4] = House.wrapText(Resources.getString(62, new String[]{buildingNames[i2], new StringBuffer().append("").append(House.scoreThresholds[i2]).toString()}), font, i);
            helpLines[i2 + 8] = House.wrapText(Resources.getString(63, new String[]{new StringBuffer().append("").append(levelThresholds[buildingUnlockLevels[i2]]).toString()}), font, i);
        }
        hintPlaceable = House.wrapText(Resources.getString(65), font, i);
        hintCellTooLow = House.wrapText(Resources.getString(66), font, i);
        hintOffGrid = House.wrapText(Resources.getString(64), font, i);
    }

    private static void showTutorialHint(int hintTypeIndex) {
        boolean[] zArr;
        int i2;
        switch (hintTypeIndex) {
            case 0:
                House.showPrompt(Resources.getString(36), null, null);
                zArr = tutorialShown;
                i2 = 0;
                break;
            case 3:
                House.showPrompt(Resources.getString(39), null, null);
                zArr = tutorialShown;
                i2 = 3;
                break;
            case 38:
                if (House.towersCompleted == 2) {
                    House.showPrompt(Resources.getString(46), null, null);
                    zArr = tutorialShown;
                    i2 = 38;
                    break;
                }
                return;
            default:
                return;
        }
        zArr[i2] = true;
        modalActive = true;
    }

    public static void handleKeyPressed(int keyCode, int gameAction) {
        int c = mapGameAction(keyCode, gameAction);
        if (c == 0) {
            keyUp = true;
        } else if (c == 1) {
            keyDown = true;
        } else if (c == 2) {
            keyLeft = true;
        } else if (c == 3) {
            keyRight = true;
        } else if (c == 5) {
            keyFire = true;
        }
        c = keyCode - 48;
        if (c >= 0 && c <= 9) {
            char[] cArr = cheatCode;
            int i3 = cheatIndex;
            cheatIndex = i3 + 1;
            if (c == cArr[i3] - 48) {
                if (cheatIndex == cheatCode.length) {
                    cheatActive = true;
                    cheatIndex = 0;
                    updateUnlocks();
                    return;
                }
                return;
            }
        }
        cheatIndex = 0;
    }

    public static void startPlacement(int cellX, int cellY, int buildingTier) {
        towerGameActive = false;
        phase = 1;
        pieceType = cellX;
        pieceValue = cellY;
        pieceVariant = buildingTier;
        cursorCol = 2;
        cursorRow = 2;
        placeDelay = 700;
        placeTimer = 0;
        cursorActive = true;
        if (cheatActive && cellY == 0) {
            randomizeBuildingStats();
        }
        if (!tutorialShown[3]) {
            House.showPrompt(new StringBuffer().append(Resources.getString(39)).append('\n').append(Resources.getString(158)).toString(), null, null);
            tutorialShown[3] = true;
            modalActive = true;
        }
        if (!tutorialShown[38] && House.towersCompleted == 2) {
            House.showPrompt(Resources.getString(46), null, null);
            tutorialShown[38] = true;
            modalActive = true;
        }
    }

    public static void paint(Graphics g) {
        if (populationGauge == null) {
            return;
        }
        int i;
        int i2;
        int i3 = 0;
        int i4 = GameMIDlet.screenHeight - 55;
        for (i = 58; i <= i4; i++) {
            g.setColor(skyGradient[0] + ((skyGradient[3] * (i - 58)) / (i4 - 58)), skyGradient[1] + ((skyGradient[4] * (i - 58)) / (i4 - 58)), skyGradient[2] + ((skyGradient[5] * (i - 58)) / (i4 - 58)));
            g.drawLine(0, i, GameMIDlet.screenWidth, i);
        }
        i = 0;
        for (i4 = 0; i4 < 18; i4++) {
            g.setColor(bandColors[i4]);
            if (i4 == 2) {
                g.fillRect(3, i, 56, 40);
            } else if (i4 == 3) {
                g.fillRect(59, i, (GameMIDlet.screenWidth - 6) - 56, 40);
                i += 40;
            } else {
                g.drawLine(3, i, GameMIDlet.screenWidth - 3, i);
                i++;
            }
        }
        i4 = GameMIDlet.screenWidth - 6;
        if (level < levelThresholds.length - 1) {
            i = level;
            while (i < levelThresholds.length - 2 && population + populationPending > levelThresholds[i + 1]) {
                i++;
            }
            i2 = levelThresholds[i];
            i = (i4 * ((population + populationPending) - i2)) / (levelThresholds[i + 1] - i2);
        } else {
            i = i4;
        }
        g.setColor(-89856);
        g.fillRect(3, 43, i, 6);
        g.setColor(-130816);
        g.fillRect(3, 49, i, 3);
        g.setClip(0, 0, 3, populationGauge.getHeight());
        g.drawImage(populationGauge, 0, 0, 20);
        g.setClip(GameMIDlet.screenWidth - 3, 0, 3, populationGauge.getHeight());
        g.drawImage(populationGauge, (GameMIDlet.screenWidth - 3) - 3, 0, 20);
        i4 = 0;
        i = -15922164;
        if (level == 0) {
            i4 = -42;
            i = -4602071;
        }
        g.setClip(8, 12, 14, statusIcons.getHeight());
        g.drawImage(statusIcons, i4 + 8, 12, 20);
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        g.setColor(-5858534);
        g.drawLine(24, 16, 53, 16);
        g.setColor(i);
        g.fillRect(24, 17, 30, 10);
        g.setColor(-1971082);
        g.drawLine(24, 27, 53, 27);
        if (level > 0) {
            drawBitmapNumber(g, digitFont, new StringBuffer().append(level).append("<20").toString(), 54, 18, 7, 6, 1, 0);
        }
        g.setClip(76, 12, 14, statusIcons.getHeight());
        g.drawImage(statusIcons, 62, 12, 20);
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        if (populationFlashTimer >= 0) {
            drawBitmapNumber(g, digitSheetCurrent, ":", 96, 16, 7, 0, 0, 0);
        }
        i4 = 0;
        while (i4 < 6) {
            i = i4 == 5 ? 3 : 5 - i4 <= populationFlashCount ? flashColorIndex : 0;
            g.setClip((i4 * 14) + 100, 10, 14, 25);
            g.drawImage(hudBarSprite, ((i4 - i) * 14) + 100, 10, 20);
            i4++;
        }
        Image image = digitSheetA;
        if (populationFlashCount == 0 && populationFlashTimer >= 0 && populationFlashTimer % 400 < 200) {
            image = digitSheetCurrent;
        }
        drawBitmapNumber(g, image, new StringBuffer().append("").append(population).toString(), 167, 16, 7, 14, 5, populationFlashCount);
        int i5 = cursorCol >= 0 ? grid[(((cursorRow * 5) + cursorCol) * 3) + 0] : 0;
        if (phase == 0 || placementResultTimer >= 0 || placementInProgress) {
            i = -3686478;
            i4 = -5399421;
            i2 = -56;
        } else {
            i = -1907998;
            i4 = -15922164;
            i2 = -28;
        }
        int i6 = GameMIDlet.screenWidth - 60;
        g.setClip(i6, 12, 14, statusIcons.getHeight());
        g.drawImage(statusIcons, i2 + i6, 12, 20);
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        int i7 = i6 + 20;
        g.setColor(i);
        g.fillRect(i7, 8, 30, 12);
        g.setColor(i4);
        g.fillRect(i7 + 1, 9, 28, 10);
        if (i5 == 0) {
            i = -3686478;
            i4 = -5399421;
        }
        g.setColor(i);
        g.fillRect(i7, 21, 30, 12);
        g.setColor(i4);
        g.fillRect(i7 + 1, 22, 28, 10);
        if (phase == 1 && placementResultTimer < 0 && !placementInProgress) {
            g.setColor(buildingColors[(pieceType + 4) - 1]);
            g.fillRect(i7 + 1, 9, 7, 10);
            g.setColor(buildingColors[pieceType - 1]);
            g.fillRect(i7 + 1, 9, 6, 9);
            drawBitmapNumber(g, digitFont, new StringBuffer().append("").append(pieceValue).toString(), i7 + 26, 9, 7, 6, 1, 0);
            if (i5 > 0) {
                g.setColor(buildingColors[(i5 + 4) - 1]);
                g.fillRect(i7 + 1, 22, 7, 10);
                g.setColor(buildingColors[i5 - 1]);
                g.fillRect(i7 + 1, 22, 6, 9);
                drawBitmapNumber(g, digitFont, new StringBuffer().append("").append(grid[(((cursorRow * 5) + cursorCol) * 3) + 1]).toString(), i7 + 26, 22, 7, 6, 1, 0);
            }
        }
        i = GameMIDlet.screenHeight - 56;
        g.setColor(-15463412);
        g.drawLine(3, i, GameMIDlet.screenWidth - 3, i);
        g.setColor(-8556946);
        g.drawLine(3, i + 1, GameMIDlet.screenWidth - 3, i + 1);
        g.setColor(-1318431);
        g.drawLine(3, i + 2, GameMIDlet.screenWidth - 3, i + 2);
        g.setColor(-1);
        g.fillRect(3, i + 3, GameMIDlet.screenWidth - 6, 51);
        g.setColor(-1318431);
        g.drawLine(3, (i + 56) - 2, GameMIDlet.screenWidth - 3, (i + 56) - 2);
        g.setColor(-8556946);
        g.drawLine(3, (i + 56) - 1, GameMIDlet.screenWidth - 3, (i + 56) - 1);
        g.setClip(0, i, 3, populationGauge.getHeight());
        g.drawImage(populationGauge, -6, i, 20);
        g.setClip(GameMIDlet.screenWidth - 3, i, 3, populationGauge.getHeight());
        g.drawImage(populationGauge, GameMIDlet.screenWidth - 12, i, 20);
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        i4 = i + 4;
        g.setColor(-16777216);
        i = hintScroll;
        while (i < hintScroll + hintLinesVisible && i < hintLines.length) {
            String str;
            Graphics graphics2;
            if (Storage.getSetting(4) == 1) {
                g.setFont(font);
                i2 = 24;
                str = hintLines[i];
                graphics2 = g;
                i3 = GameMIDlet.screenWidth - 10;
                i6 = i4;
            } else {
                g.setFont(font);
                i2 = 20;
                str = hintLines[i];
                graphics2 = g;
                i3 = 10;
                i6 = i4;
            }
            graphics2.drawString(str, i3, i6, i2);
            i4 += font.getHeight();
            i++;
        }
        i = 0;
        if (placementResultTimer >= 0) {
            i = placementResultTimer - 750;
            if (i > 0) {
                i = -i;
            }
            i += 750;
        }
        int i8 = (((((GameMIDlet.screenWidth - 28) - 11) - 173) >> 1) + 28) + 11;
        i5 = (((((GameMIDlet.screenHeight - 60) - 56) - 173) + 1) >> 1) + 60;
        if (phase == 1) {
            i4 = 0;
            if (placementResultTimer >= 0) {
                i4 = ((-((i8 - 37) + (buildIcon.getWidth() << 1))) * i) / 750;
            }
            g.drawImage(buildIcon, i4 + (i8 - 37), i5 + 140, 20);
        }
        g.setColor(-1);
        g.fillRect(i8, i5, 173, 173);
        g.setColor(-12566464);
        g.fillRect(i8 + 1, i5 + 1, 171, 171);
        i4 = selectedBuilding;
        if (phase == 1) {
            i4 = pieceType - 1;
        }
        i6 = (cellPalette[i4] >> 16) & 255;
        i3 = (cellPalette[i4] >> 8) & 255;
        int i9 = cellPalette[i4] & 255;
        int i10 = (cellPalette[i4 + 4] >> 16) & 255;
        int i11 = (cellPalette[i4 + 4] >> 8) & 255;
        i7 = cellPalette[i4 + 4] & 255;
        i2 = blinkTimer800 - 400;
        if (i2 > 0) {
            i2 = -i2;
        }
        i2 += 400;
        i6 = (((i6 + (((i10 - i6) * i2) / 400)) << 16) | ((i3 + (((i11 - i3) * i2) / 400)) << 8)) | (((i2 * (i7 - i9)) / 400) + i9);
        for (i10 = 0; i10 < 5; i10++) {
            for (i9 = 0; i9 < 5; i9++) {
                i3 = tierColorsSecondary[unlockedBuildingTier];
                i2 = tierColorsPrimary[unlockedBuildingTier];
                if (cellLevels[(i10 * 5) + i9] >= i4 && !placementInProgress && placementResultTimer < 0 && selectedBuilding <= unlockedBuildingTier && (placeDelay < 0 || phase != 1)) {
                    i2 = i6;
                    i3 = i6;
                }
                g.setColor(tierColorsPrimary[unlockedBuildingTier]);
                g.fillRect((i8 + 5) + (i9 * 34), (i5 + 5) + (i10 * 34), 27, 27);
                g.setColor(i3);
                g.fillRect(((i8 + 5) + 1) + (i9 * 34), ((i5 + 5) + 1) + (i10 * 34), 25, 25);
                g.setColor(i2);
                g.fillRect(((i8 + 5) + 2) + (i9 * 34), ((i5 + 5) + 2) + (i10 * 34), 23, 23);
                g.setColor(tierColorsSecondary[unlockedBuildingTier]);
                g.fillRect(((i8 + 5) + 3) + (i9 * 34), ((i5 + 5) + 3) + (i10 * 34), 21, 21);
            }
        }
        g.setStrokeStyle(1);
        g.setColor(-7171438);
        for (i4 = 0; i4 < 4; i4++) {
            i2 = (((i8 + 5) + 27) + 3) + (i4 * 34);
            g.drawLine(i2, (i5 + 5) + 1, i2, ((i5 + 173) - 5) - 2);
        }
        for (i4 = 0; i4 < 4; i4++) {
            i2 = (((i5 + 5) + 27) + 3) + (i4 * 34);
            g.drawLine((i8 + 5) + 1, i2, ((i8 + 173) - 5) - 2, i2);
        }
        g.setStrokeStyle(0);
        g.setColor(-13108);
        i6 = (animTick * 32) >> 8;
        for (i2 = 0; i2 < 4; i2++) {
            i3 = (highlightCells[(i2 * 2) + 0] * 34) + (i5 + 5);
            i9 = (((i5 + 5) + (highlightCells[(i2 * 2) + 1] * 34)) - 7) - 1;
            i10 = (i2 * 34) + (((i8 + 5) + 27) + 3);
            for (i4 = i3 + i6; i4 < i9; i4 += 9) {
                g.drawLine(i10 - 1, i4, i10 - 1, i4 + 1);
            }
            for (i4 = i9 - i6; i4 > i3; i4 -= 9) {
                g.drawLine(i10 + 1, i4, i10 + 1, i4 + 1);
            }
        }
        for (i2 = 0; i2 < 4; i2++) {
            i3 = (highlightCells[((i2 * 2) + 8) + 0] * 34) + (i8 + 5);
            i9 = (((i8 + 5) + (highlightCells[((i2 * 2) + 8) + 1] * 34)) - 7) - 1;
            i10 = (i2 * 34) + (((i5 + 5) + 27) + 3);
            for (i4 = i3 + i6; i4 < i9; i4 += 9) {
                g.drawLine(i4, i10 + 1, i4 + 1, i10 + 1);
            }
            for (i4 = i9 - i6; i4 > i3; i4 -= 9) {
                g.drawLine(i4, i10 - 1, i4 + 1, i10 - 1);
            }
        }
        for (i4 = 0; i4 < 25; i4++) {
            i2 = grid[(i4 * 3) + 0];
            i6 = grid[(i4 * 3) + 2];
            if (i2 != 0) {
                i3 = (i8 + 8) + ((i4 % 5) * 34);
                i9 = (((i5 + 29) + ((i4 / 5) * 34)) - buildingSprites[i2 - 1].getHeight()) + 0;
                g.setClip(i3, i9, buildingSprites[i2 - 1].getWidth() / 4, buildingSprites[i2 - 1].getHeight());
                g.drawImage(buildingSprites[i2 - 1], i3 - ((buildingSprites[i2 - 1].getWidth() / 4) * i6), i9, 20);
            }
        }
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        if (phase == 1 && placementResultTimer < 0) {
            Image image2 = null;
            if (cursorCol >= 0) {
                i6 = (i8 + 8) + (cursorCol * 34);
                i2 = i5 + 29;
                i4 = cursorRow * 34;
            } else {
                i6 = (i8 - 37) + 5;
                i2 = i5 + 140;
                i4 = 21;
            }
            i4 += i2;
            if (placementInProgress) {
                i3 = placeAnimTimer - 2250;
                if (i3 > 0 && cursorCol >= 0) {
                    i6 += (i3 * 9) / 750;
                    i4 += (i3 * -9) / 750;
                }
                if (cursorCol >= 0) {
                    if (targetCellEmpty) {
                        i2 = 6;
                    } else {
                        i3 = placeAnimTimer - 1500;
                        i2 = 4;
                    }
                    i2 = 5 - ((i2 * i3) / 750);
                    if (i3 >= 0 && i3 < 750) {
                        g.setClip(i6 - 10, i4 - 31, tileEffectFrames.getWidth() / 6, tileEffectFrames.getHeight());
                        i10 = i4;
                        i11 = i6;
                        i6 = (i6 - 10) - (i2 * (tileEffectFrames.getWidth() / 6));
                        image2 = tileEffectFrames;
                        i2 = i4;
                        i4 = -31;
                    }
                } else {
                    if (i3 >= 0 && i3 < 750) {
                        i2 = 5 - ((i3 * 6) / 750);
                        g.setClip(i6 - 8, i4 - 24, dustEffectFrames.getWidth() / 6, dustEffectFrames.getHeight());
                        i10 = i4;
                        i11 = i6;
                        i6 = (i6 - 8) - (i2 * (dustEffectFrames.getWidth() / 6));
                        image2 = dustEffectFrames;
                        i2 = i4;
                        i4 = -24;
                    }
                }
            } else {
                i2 = i6 + 9;
                i6 = i4 - 9;
                if (placeDelay >= 0) {
                    i3 = i2 + ((placeDelay * 20) / 700);
                    i2 = placeDelay * -20;
                    i4 = 700;
                } else {
                    i4 = placeTimer - 1000;
                    if (i4 > 0) {
                        i4 = -i4;
                    }
                    i4 += 1000;
                    i3 = ((i4 * -3) / 1001) + i2;
                    i2 = i4 * 3;
                    i4 = 1001;
                }
                i4 = (i2 / i4) + i6;
                if (slideTimer > 0) {
                    i3 += (slideX * slideTimer) / 150;
                    i4 += (slideY * slideTimer) / 150;
                }
                if (placeDelay < 0) {
                    g.setClip(i3 - 2, i4 - 27, buildingSprites[4].getWidth() / 5, buildingSprites[4].getHeight());
                    i6 = (i3 - 2) - ((buildingSprites[4].getWidth() / 5) << 2);
                    i10 = i4;
                    i11 = i3;
                    image2 = buildingSprites[4];
                    i2 = i4;
                    i4 = -27;
                } else {
                    i6 = i3;
                }
            }
            if (image2 != null) {
                g.drawImage(image2, i6, i4 + i2, 20);
                i4 = i10;
                i6 = i11;
            }
            if (!placementInProgress || cursorCol >= 0) {
                i4 = (i4 - buildingSprites[pieceType - 1].getHeight()) + 0;
                g.setClip(i6, i4, buildingSprites[pieceType - 1].getWidth() / 4, buildingSprites[pieceType - 1].getHeight());
                g.drawImage(buildingSprites[pieceType - 1], i6 - (pieceVariant * (buildingSprites[pieceType - 1].getWidth() / 4)), i4, 20);
            }
            g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
        }
        i4 = i8 - 39;
        i3 = i5 + 0;
        if (phase == 0) {
            i = placementResultTimer >= 0 ? ((i * (-(i4 + 56))) / 750) + i4 : i4;
            g.setColor(-8556946);
            g.fillRect(i, i3, 28, 100);
            g.setColor(-1);
            g.fillRect(i + 1, i3 + 1, 26, 98);
            for (i6 = 0; i6 < 4; i6++) {
                i4 = i + 3;
                i9 = (i6 * 24) + (i3 + 3);
                g.setColor(-5460820);
                if (i6 == selectedBuilding && blinkTimer < 250) {
                    g.setColor(-89856);
                }
                g.fillRect(i4, i9, 22, 22);
                if (i6 <= unlockedSpecialTier) {
                    g.setClip(i4 - 5, i9 + 12, 10, statusIcons.getHeight());
                    g.drawImage(statusIcons, (((i4 - 5) - 28) - 14) - 28, i9 + 12, 20);
                }
                if (i6 <= unlockedBuildingTier) {
                    i2 = i4 + 3;
                    i4 = i9 + 19;
                    if (i6 == selectedBuilding) {
                        if (launchDelay < 250) {
                            i2 += 2;
                            i4 -= 2;
                        }
                        g.setClip(i2 - 2, i4 - 27, buildingSprites[4].getWidth() / 5, buildingSprites[4].getHeight());
                        g.drawImage(buildingSprites[4], (i2 - 2) - ((buildingSprites[4].getWidth() / 5) * i6), i4 - 27, 20);
                    }
                    i4 = (i4 - buildingSprites[i6].getHeight()) + 0;
                    g.setClip(i2, i4, buildingSprites[i6].getWidth() / 4, buildingSprites[i6].getHeight());
                    g.drawImage(buildingSprites[i6], i2 - ((buildingSprites[i6].getWidth() / 4) * 3), i4, 20);
                }
                g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
            }
        }
        if (modalActive) {
            House.paintTutorialModal(g);
        }
        needsRepaint = false;
    }

    private static void drawBitmapNumber(Graphics g, Image fontImage, String numberStr, int x, int y, int digitWidth, int digitHeight, int charSpacing, int anchor) {
        if (fontImage == null) {
            return;
        }
        char[] toCharArray = numberStr.toCharArray();
        int height = fontImage.getHeight();
        int length = toCharArray.length;
        if (charSpacing <= length) {
            charSpacing = length;
        }
        int length2 = (toCharArray.length - 1) - anchor;
        while (anchor < charSpacing) {
            length = length2 >= 0 ? toCharArray[length2] - 48 : 0;
            g.setClip((x - (anchor * digitHeight)) - digitWidth, y, digitWidth, height);
            g.drawImage(fontImage, ((x - (anchor * digitHeight)) - digitWidth) - (length * digitWidth), y, 20);
            anchor++;
            length2--;
        }
        g.setClip(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
    }

    public static void enterCity() {
        towerGameActive = false;
        phase = 0;
        cursorCol = 2;
        cursorRow = 2;
        population = 0;
        populationFlashCount = 0;
        cheatIndex = 0;
        unlockedSpecialTier = -1;
        modalActive = false;
        needsRepaint = true;
        slideTimer = 0;
        placementInProgress = false;
        populationFlashTimer = 0;
        populationPending = 0;
        highScorePending = false;
        population = 0;
        launchDelay = -1;
        placementResultTimer = -1;
        CityMode.loadState();
        updateUnlocks();
        recalculateSynergies();
        selectedBuilding = 0;
        if (!tutorialShown[0]) {
            House.showPrompt(Resources.getString(36), null, null);
            tutorialShown[0] = true;
            modalActive = true;
        }
    }

    public static void update(int deltaMs, int totalTimeMs) {
        int i3 = 0;
        int i4;
        String message = null;
        String[] strArr;
        if (slideTimer >= 0) {
            slideTimer -= deltaMs;
        }
        if (populationFlashTimer >= 0) {
            populationFlashTimer -= deltaMs;
            if (populationFlashCount > 0) {
                flashColorIndex = (flashColorIndex % 2) + 1;
                if (populationFlashTimer < 0) {
                    populationFlashCount--;
                    populationFlashTimer = populationFlashCount > 0 ? 300 : 1199;
                }
            }
        }
        animTick += deltaMs >> 4;
        animTick %= 71;
        if (phase == 0) {
            blinkTimer += deltaMs;
            blinkTimer %= 500;
        }
        blinkTimer800 += deltaMs;
        blinkTimer800 %= 800;
        if (!(modalActive || tutorialShown[1])) {
            House.showPrompt(Resources.getString(37), null, null);
            tutorialShown[1] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[2])) {
            House.showPrompt(Resources.getString(38), null, null);
            tutorialShown[2] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[5] || level != 1)) {
            House.showPrompt(Resources.getString(41, new String[]{new StringBuffer().append("").append(levelThresholds[level]).toString()}), null, null);
            tutorialShown[5] = true;
            modalActive = true;
        }
        if (!modalActive && !tutorialShown[level + 4] && level > 1 && level < 20) {
            switch (level) {
                case 5:
                    i3 = 58;
                    break;
                case 17:
                    i3 = 59;
                    break;
                case 19:
                    i3 = 60;
                    break;
                default:
                    i3 = 43;
                    break;
            }
            House.showPrompt(Resources.getString(i3, new String[]{new StringBuffer().append("").append(levelThresholds[level]).toString()}), null, null);
            tutorialShown[level + 4] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[43] || level != 20)) {
            House.showPrompt(Resources.getString(52), null, null);
            tutorialShown[43] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[24] || unlockedBonusTier != 1)) {
            House.showPrompt(Resources.getString(45), null, levelBadges[0]);
            levelBadgeIndex = 0;
            tutorialShown[24] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[unlockedBonusTier + 23] || unlockedBonusTier <= 1)) {
            House.showPrompt(Resources.getString(44, new String[]{levelNames[unlockedBonusTier - 1], levelNames[unlockedBonusTier]}), null, levelBadges[unlockedBonusTier - 1]);
            levelBadgeIndex = unlockedBonusTier - 1;
            tutorialShown[unlockedBonusTier + 23] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[33] || level != 2)) {
            House.showPrompt(Resources.getString(42), null, null);
            tutorialShown[33] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[unlockedBuildingTier + 33] || unlockedBuildingTier <= 0 || placementInProgress)) {
            House.showPrompt(unlockMessages[unlockedBuildingTier - 1], null, null);
            tutorialShown[unlockedBuildingTier + 33] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[37] || !tutorialShown[34])) {
            House.showPrompt(Resources.getString(48), null, null);
            tutorialShown[37] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[unlockedSpecialTier + 39] || unlockedSpecialTier < 0 || placementInProgress)) {
            House.showPrompt(Resources.getString(51, new String[]{buildingNames[unlockedSpecialTier + 4]}), null, null);
            tutorialShown[unlockedSpecialTier + 39] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[44] || !buildingHintPending)) {
            House.showPrompt(Resources.getString(53, new String[]{levelNames[unlockedBonusTier]}), null, null);
            tutorialShown[44] = true;
            modalActive = true;
        }
        if (!(modalActive || tutorialShown[45] || !tutorialShown[44])) {
            House.showPrompt(Resources.getString(54, new String[]{new StringBuffer().append("").append(levelThresholds[20]).toString()}), null, null);
            tutorialShown[45] = true;
            modalActive = true;
        }
        if (launchDelay >= 0) {
            launchDelay -= deltaMs;
            if (launchDelay < 0) {
                towerGameActive = true;
                clearInputState();
            }
        }
        if (phase == 1) {
            if (placeDelay >= 0) {
                placeDelay -= deltaMs;
            } else {
                placeTimer += deltaMs;
                placeTimer %= 2000;
            }
        }
        if (placementResultTimer >= 0) {
            placementResultTimer -= deltaMs;
            if (phase == 1 && placementResultTimer < 750) {
                phase = 0;
            }
        }
        if (placementInProgress && !modalActive) {
            boolean z = cursorActive;
            placeAnimTimer -= deltaMs;
            if (cursorCol >= 0) {
                populationPending = 0;
                if (placeAnimTimer > 0) {
                    populationPending = ((populationTarget - population) * placeAnimTimer) / 3000;
                }
            }
            if (placeAnimTimer < 0 && cursorActive && cursorCol >= 0) {
                grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = pieceType;
                grid[(((cursorRow * 5) + cursorCol) * 3) + 1] = pieceValue;
                grid[(((cursorRow * 5) + cursorCol) * 3) + 2] = pieceVariant;
                CityMode.saveState();
                updateUnlocks();
                z = false;
            } else if (placeAnimTimer < 0) {
                if (cursorCol >= 0) {
                    highScorePending = true;
                    recalculateSynergies();
                }
                if (!tutorialShown[4]) {
                    House.showPrompt(Resources.getString(40), null, null);
                    tutorialShown[4] = true;
                    modalActive = true;
                }
                placementResultTimer = 1500;
                placementInProgress = false;
                z = true;
            }
            cursorActive = z;
        }
        if (modalActive) {
            if (keyUp) {
                House.handleModalAction(-1);
                clearInputState();
            } else if (keyDown) {
                if (House.handleModalAction(1)) {
                    modalActive = false;
                    needsRepaint = true;
                    CityMode.saveState();
                }
                clearInputState();
            } else if (keyFire) {
                if (House.handleModalAction(0)) {
                    modalActive = false;
                    needsRepaint = true;
                    CityMode.saveState();
                }
                clearInputState();
            }
        } else if (phase == 0) {
            if (launchDelay < 0 && placementResultTimer < 0) {
                if (keyUp) {
                    if (selectedBuilding > 0) {
                        selectedBuilding--;
                    }
                } else if (keyDown) {
                    if (selectedBuilding < 3) {
                        selectedBuilding++;
                    }
                } else if (keyFire && ((selectedBuilding <= unlockedBuildingTier && selectedBuilding <= highestTierUnlocked) || cheatActive)) {
                    launchDelay = 500;
                }
            }
        } else if (placeDelay < 0 && !placementInProgress && placementResultTimer < 0) {
            if (keyUp) {
                if (cursorCol >= 0 && cursorRow > 0) {
                    cursorRow--;
                    slideX = 0;
                    slideY = 34;
                    slideTimer = 150;
                }
            } else if (keyDown) {
                if (cursorRow < 4) {
                    cursorRow++;
                    slideX = 0;
                    slideY = -34;
                    slideTimer = 150;
                }
            } else if (keyLeft) {
                if (cursorCol > 0) {
                    cursorCol--;
                    slideX = 34;
                    slideY = 0;
                    slideTimer = 150;
                } else if (cursorCol == 0) {
                    slideX = ((cursorCol * 34) + 8) + 32;
                    slideY = ((cursorRow * 34) + 29) - 161;
                    cursorCol--;
                    cursorRow = 4;
                    slideTimer = 150;
                }
            } else if (keyRight) {
                if (cursorCol < 0) {
                    cursorCol++;
                    slideX = -32 - ((cursorCol * 34) + 8);
                    slideY = 161 - ((cursorRow * 34) + 29);
                    slideTimer = 150;
                } else if (cursorCol < 4) {
                    cursorCol++;
                    slideX = -34;
                    slideY = 0;
                    slideTimer = 150;
                }
            } else if (keyFire && (cursorCol < 0 || cellLevels[(cursorRow * 5) + cursorCol] >= pieceType - 1 || cheatActive)) {
                placementInProgress = true;
                placeAnimTimer = 3000;
                if (cursorCol >= 0) {
                    populationTarget = population;
                    population -= grid[(((cursorRow * 5) + cursorCol) * 3) + 1];
                    population += pieceValue;
                    populationPending = populationTarget - population;
                    digitSheetCurrent = digitSheetA;
                    i4 = GameMIDlet.screenWidth - 20;
                    if (population > populationTarget) {
                        message = Resources.getString(67, new String[]{new StringBuffer().append("").append(population - populationTarget).toString()});
                    } else if (population < populationTarget) {
                        message = Resources.getString(69, new String[]{new StringBuffer().append("").append(populationTarget - population).toString()});
                        digitSheetCurrent = digitSheetB;
                        populationFlashCount = 0;
                        if (population / 10000 != populationTarget / 10000) {
                            i3 = 5;
                        } else if (population / 1000 != populationTarget / 1000) {
                            i3 = 4;
                        } else if (population / 100 != populationTarget / 100) {
                            i3 = 3;
                        } else if (population / 10 == populationTarget / 10) {
                            i3 = 2;
                        } else {
                            if (population != populationTarget) {
                                i3 = 1;
                            }
                            if (populationFlashCount > 0) {
                                populationFlashTimer = 300;
                            }
                            targetCellEmpty = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                            grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                        }
                        populationFlashCount = i3;
                        if (populationFlashCount > 0) {
                            populationFlashTimer = 300;
                        }
                        if (grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0) {
                        }
                        targetCellEmpty = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                        grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                    } else {
                        message = Resources.getString(68);
                    }
                    hintCustom = House.wrapText(message, font, i4);
                    populationFlashCount = 0;
                    if (population / 10000 != populationTarget / 10000) {
                        i3 = 5;
                    } else if (population / 1000 != populationTarget / 1000) {
                        i3 = 4;
                    } else if (population / 100 != populationTarget / 100) {
                        i3 = 3;
                    } else if (population / 10 == populationTarget / 10) {
                        if (population != populationTarget) {
                            i3 = 1;
                        }
                        if (populationFlashCount > 0) {
                            populationFlashTimer = 300;
                        }
                        if (grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0) {
                        }
                        targetCellEmpty = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                        grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                    } else {
                        i3 = 2;
                    }
                    populationFlashCount = i3;
                    if (populationFlashCount > 0) {
                        populationFlashTimer = 300;
                    }
                    if (grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0) {
                    }
                    targetCellEmpty = grid[(((cursorRow * 5) + cursorCol) * 3) + 0] == 0;
                    grid[(((cursorRow * 5) + cursorCol) * 3) + 0] = 0;
                }
            }
        }
        clearInputState();
        Object obj = hintLines;
        if (helpLines == null) {
            helpLines = new String[12][];
        }
        if (phase == 0) {
            strArr = selectedBuilding <= unlockedSpecialTier ? helpLines[selectedBuilding + 4] : selectedBuilding <= unlockedBuildingTier ? helpLines[selectedBuilding] : helpLines[selectedBuilding + 8];
        } else {
            if (hintCustom == null) {
                i4 = GameMIDlet.screenWidth - 20;
                if (population > populationTarget) {
                    message = Resources.getString(67, new String[]{new StringBuffer().append("").append(population - populationTarget).toString()});
                } else if (population < populationTarget) {
                    message = Resources.getString(69, new String[]{new StringBuffer().append("").append(populationTarget - population).toString()});
                    digitSheetCurrent = digitSheetB;
                } else {
                    message = Resources.getString(68);
                }
                hintCustom = House.wrapText(message, font, i4);
            }
            strArr = cursorCol < 0 ? hintOffGrid : (placementInProgress || placementResultTimer >= 0) ? hintCustom : cellLevels[(cursorRow * 5) + cursorCol] < pieceType + -1 ? hintCellTooLow : hintPlaceable;
        }
        hintLines = strArr;
        hintScrollTimer -= totalTimeMs;
        if (!hintLines.equals(obj)) {
            hintScrollTimer = 2000;
            hintScroll = 0;
        }
        if (hintScrollTimer < 0 && hintLines.length > hintLinesVisible) {
            hintScrollTimer = 2000;
            hintScroll += hintLinesVisible;
            if (hintScroll >= hintLines.length + hintLinesVisible) {
                hintScroll = 0;
            }
        }
    }

    private static int mapGameAction(int keyCode, int gameAction) {
        if (keyCode == 50) {
            return 0;
        }
        if (keyCode == 56) {
            return 1;
        }
        if (keyCode == 52) {
            return 2;
        }
        if (keyCode == 54) {
            return 3;
        }
        if (keyCode != 53) {
            if (gameAction == 1) {
                return 0;
            }
            if (gameAction == 6) {
                return 1;
            }
            if (gameAction == 2) {
                return 2;
            }
            if (gameAction == 5) {
                return 3;
            }
            if (gameAction != 8) {
                return -1;
            }
        }
        return 5;
    }

    public static volatile boolean assetsLoaded = false;

    public static boolean loadAssets() {
        assetsLoaded = false;
        House.soundPlayer.stopAll();
        populationGauge = Resources.getImage(21);
        statusIcons = Resources.getImage(22);
        hudBarSprite = Resources.getImage(23);
        if (!House.updateLoadingProgress(20)) {
            return false;
        }
        buildIcon = Resources.getImage(24);
        buildingSprites = new Image[5];
        buildingSprites[0] = Resources.getImage(25);
        if (!House.updateLoadingProgress(25)) {
            return false;
        }
        buildingSprites[1] = Resources.getImage(26);
        buildingSprites[2] = Resources.getImage(27);
        buildingSprites[3] = Resources.getImage(28);
        if (!House.updateLoadingProgress(30)) {
            return false;
        }
        buildingSprites[4] = Resources.getImage(29);
        tileEffectFrames = Resources.getImage(30);
        if (!House.updateLoadingProgress(40)) {
            return false;
        }
        dustEffectFrames = Resources.getImage(31);
        digitFont = Resources.getImage(15);
        digitSheetA = Resources.getImage(16);
        digitSheetCurrent = digitSheetA;
        if (!House.updateLoadingProgress(50)) {
            return false;
        }
        digitSheetB = Resources.getImage(17);
        levelBadges = new Image[9];
        levelBadges[0] = Resources.getImage(71);
        if (!House.updateLoadingProgress(60)) {
            return false;
        }
        levelBadges[1] = Resources.getImage(72);
        levelBadges[2] = Resources.getImage(73);
        if (!House.updateLoadingProgress(70)) {
            return false;
        }
        levelBadges[3] = Resources.getImage(74);
        levelBadges[4] = Resources.getImage(75);
        if (!House.updateLoadingProgress(80)) {
            return false;
        }
        levelBadges[5] = Resources.getImage(76);
        levelBadges[6] = Resources.getImage(77);
        if (!House.updateLoadingProgress(90)) {
            return false;
        }
        levelBadges[7] = Resources.getImage(78);
        levelBadges[8] = Resources.getImage(79);
        if (!House.updateLoadingProgress(100)) {
            return false;
        }
        levelNames = new String[]{"-", Resources.getString(71), Resources.getString(72), Resources.getString(73), Resources.getString(74), Resources.getString(75), Resources.getString(76), Resources.getString(77), Resources.getString(78), Resources.getString(79)};
        unlockMessages = new String[]{Resources.getString(47), Resources.getString(49), Resources.getString(50)};
        assetsLoaded = true;
        return true;
    }

    public static int getBuildingTowerType() {
        return unlockedSpecialTier >= selectedBuilding ? selectedBuilding + 5 : selectedBuilding + 1;
    }

    public static void clearInputState() {
        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;
        keyFire = false;
    }

    public static boolean isTowerGameActive() {
        return towerGameActive;
    }

    public static void updateUnlocks() {
        int i;
        int i2 = 0;
        for (i = 0; i < levelThresholds.length; i++) {
            if (population + populationPending >= levelThresholds[i]) {
                level = i;
            }
        }
        int i3 = unlockedBuildingTier;
        for (i = 0; i < buildingUnlockLevels.length; i++) {
            if (level >= buildingUnlockLevels[i] || cheatActive) {
                unlockedBuildingTier = i;
            }
            if (level >= specialUnlockLevels[i] || cheatActive) {
                unlockedSpecialTier = i;
            }
        }
        if (i3 != unlockedBuildingTier) {
            selectedBuilding = unlockedBuildingTier;
        }
        while (i2 < bonusUnlockLevels.length) {
            if (level >= bonusUnlockLevels[i2]) {
                unlockedBonusTier = i2;
            }
            i2++;
        }
    }

    public static void recalculateSynergies() {
        int i;
        int i2;
        int[] iArr = new int[4];
        boolean[] zArr = new boolean[3];
        highestTierUnlocked = 0;
        buildingHintPending = true;
        for (i = 0; i < 5; i++) {
            for (i2 = 0; i2 < 5; i2++) {
                int i3;
                for (i3 = 0; i3 < iArr.length; i3++) {
                    iArr[i3] = 0;
                }
                for (i3 = 0; i3 < zArr.length; i3++) {
                    zArr[i3] = false;
                }
                if (i2 - 1 >= 0) {
                    iArr[0] = grid[(((i * 5) + (i2 - 1)) * 3) + 0];
                }
                if (i2 + 1 < 5) {
                    iArr[1] = grid[(((i * 5) + (i2 + 1)) * 3) + 0];
                }
                if (i - 1 >= 0) {
                    iArr[2] = grid[((((i - 1) * 5) + i2) * 3) + 0];
                }
                if (i + 1 < 5) {
                    iArr[3] = grid[((((i + 1) * 5) + i2) * 3) + 0];
                }
                i3 = 0;
                while (i3 < iArr.length) {
                    if (iArr[i3] > 0 && iArr[i3] < 4) {
                        zArr[iArr[i3] - 1] = true;
                    }
                    i3++;
                }
                i3 = (zArr[0] && zArr[1] && zArr[2]) ? 3 : (zArr[0] && zArr[1]) ? 2 : zArr[0] ? 1 : 0;
                cellLevels[(i * 5) + i2] = i3;
                if (i3 > highestTierUnlocked) {
                    highestTierUnlocked = i3;
                }
                if (grid[(((i * 5) + i2) * 3) + 0] == 0) {
                    buildingHintPending = false;
                }
            }
        }
        // horizontal links: for each column pair (c, c+1) find the first/last row where both cells are built
        for (int c = 0; c < 4; c++) {
            int first = -1;
            int last = -1;
            for (int r = 0; r < 5; r++) {
                int left = grid[(((r * 5) + c) * 3) + 0];
                int right = grid[((((r * 5) + c) + 1) * 3) + 0];
                if (left > 0 && right > 0) {
                    if (first < 0) {
                        first = r;
                    }
                    last = r + 1;
                }
            }
            highlightCells[(c * 2) + 0] = first;
            highlightCells[(c * 2) + 1] = last;
        }
        // vertical links: for each row pair (r, r+1) find the first/last column where both cells are built
        for (int r = 0; r < 4; r++) {
            int first = -1;
            int last = -1;
            for (int c = 0; c < 5; c++) {
                int upper = grid[(((r * 5) + c) * 3) + 0];
                int lower = grid[((((r + 1) * 5) + c) * 3) + 0];
                if (upper > 0 && lower > 0) {
                    if (first < 0) {
                        first = c;
                    }
                    last = c + 1;
                }
            }
            highlightCells[((r * 2) + 8) + 0] = first;
            highlightCells[((r * 2) + 8) + 1] = last;
        }
    }

    public static boolean isCheatActive() {
        return cheatActive;
    }

    public static boolean consumeHighScoreFlag() {
        boolean z = highScorePending;
        highScorePending = false;
        return z;
    }

    public static int getPopulation() {
        return population;
    }

    public static void unloadAssets() {
        populationGauge = null;
        statusIcons = null;
        hudBarSprite = null;
        buildIcon = null;
        buildingSprites = null;
        tileEffectFrames = null;
        dustEffectFrames = null;
        digitFont = null;
        digitSheetA = null;
        digitSheetB = null;
        levelBadges = null;
        levelNames = null;
        unlockMessages = null;
        System.gc();
    }

    /** Restores the city-mode state from the "citymode" record (CityMode::loadState()). */
    public static void loadState() {
        try {
            DataInputStream in = Storage.openRead("citymode");
            if (in == null) {
                return;
            }
            House.gameMode = in.readInt();
            House.screenState = in.readInt();
            phase = in.readInt();
            towerGameActive = in.readBoolean();
            level = in.readInt();
            unlockedBonusTier = in.readInt();
            levelBadgeIndex = in.readInt();
            population = in.readInt();
            populationTarget = in.readInt();
            populationPending = in.readInt();
            pieceValue = in.readInt();
            cursorCol = in.readInt();
            cursorRow = in.readInt();
            pieceType = in.readInt();
            pieceVariant = in.readInt();
            placementInProgress = in.readBoolean();
            placeAnimTimer = in.readInt();
            flashColorIndex = in.readInt();
            populationFlashTimer = in.readInt();
            placementResultTimer = in.readInt();
            populationFlashCount = in.readInt();
            selectedBuilding = in.readInt();
            blinkTimer = in.readInt();
            blinkTimer800 = in.readInt();
            launchDelay = in.readInt();
            placeDelay = in.readInt();
            placeTimer = in.readInt();
            slideTimer = in.readInt();
            slideX = in.readInt();
            slideY = in.readInt();
            unlockedBuildingTier = in.readInt();
            highestTierUnlocked = in.readInt();
            unlockedSpecialTier = in.readInt();
            animTick = in.readInt();
            targetCellEmpty = in.readBoolean();
            modalActive = in.readBoolean();
            for (int i = 0; i < 16; i++) {
                highlightCells[i] = in.readInt();
            }
            for (int i = 0; i < 25; i++) {
                cellLevels[i] = in.readInt();
            }
            for (int i = 0; i < 25; i++) {
                grid[(i * 3) + 0] = in.readByte();
                grid[(i * 3) + 1] = in.readInt();
                grid[(i * 3) + 2] = in.readByte();
            }
            for (int i = 0; i < tutorialShown.length; i++) {
                tutorialShown[i] = in.readBoolean();
            }
            reservedZ = in.readInt();
            tutorialShown[reservedZ] = in.readBoolean();
            Storage.close();
            if (modalActive) {
                modalActive = false;
            }
            showTutorialHint(reservedZ);
        } catch (Exception ex) {
            System.out.println(new StringBuffer().append("Exception in CityMode::loadState(), e:").append(ex.getMessage()).toString());
        }
    }

    /** Persists the city-mode state to the "citymode" record (CityMode::saveState()). Skipped while the cheat is active. */
    public static void saveState() {
        if (cheatActive) {
            return;
        }
        try {
            DataOutputStream out = Storage.openWrite("citymode");
            out.writeInt(House.gameMode);
            out.writeInt(House.screenState);
            out.writeInt(phase);
            out.writeBoolean(towerGameActive);
            out.writeInt(level);
            out.writeInt(unlockedBonusTier);
            out.writeInt(levelBadgeIndex);
            out.writeInt(population);
            out.writeInt(populationTarget);
            out.writeInt(populationPending);
            out.writeInt(pieceValue);
            out.writeInt(cursorCol);
            out.writeInt(cursorRow);
            out.writeInt(pieceType);
            out.writeInt(pieceVariant);
            out.writeBoolean(placementInProgress);
            out.writeInt(placeAnimTimer);
            out.writeInt(flashColorIndex);
            out.writeInt(populationFlashTimer);
            out.writeInt(placementResultTimer);
            out.writeInt(populationFlashCount);
            out.writeInt(selectedBuilding);
            out.writeInt(blinkTimer);
            out.writeInt(blinkTimer800);
            out.writeInt(launchDelay);
            out.writeInt(placeDelay);
            out.writeInt(placeTimer);
            out.writeInt(slideTimer);
            out.writeInt(slideX);
            out.writeInt(slideY);
            out.writeInt(unlockedBuildingTier);
            out.writeInt(highestTierUnlocked);
            out.writeInt(unlockedSpecialTier);
            out.writeInt(animTick);
            out.writeBoolean(targetCellEmpty);
            out.writeBoolean(modalActive);
            for (int i = 0; i < 16; i++) {
                out.writeInt(highlightCells[i]);
            }
            for (int i = 0; i < 25; i++) {
                out.writeInt(cellLevels[i]);
            }
            for (int i = 0; i < 25; i++) {
                out.writeByte(grid[(i * 3) + 0]);
                out.writeInt(grid[(i * 3) + 1]);
                out.writeByte(grid[(i * 3) + 2]);
            }
            for (int i = 0; i < tutorialShown.length; i++) {
                out.writeBoolean(tutorialShown[i]);
                if (i == 0 && tutorialShown[i]) {
                    reservedZ = 0;
                } else if (i != 0 && !tutorialShown[i] && tutorialShown[i - 1]) {
                    reservedZ = i - 1;
                }
            }
            if (modalActive) {
                tutorialShown[reservedZ] = false;
            }
            out.writeInt(reservedZ);
            out.writeBoolean(tutorialShown[reservedZ]);
            Storage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in CityMode::saveState(), e:").append(ex.getMessage()).toString());
        }
    }

    private static void randomizeBuildingStats() {
        pieceVariant = House.random(3);
        pieceValue = ((pieceType - 1) * 100) + House.random(pieceType * 100);
    }

    public static boolean handleClick(int px, int py) {
        if (modalActive) {
            keyFire = true;
            return true;
        }
        int i8 = (((((GameMIDlet.screenWidth - 28) - 11) - 173) >> 1) + 28) + 11;
        int i5 = (((((GameMIDlet.screenHeight - 60) - 56) - 173) + 1) >> 1) + 60;
        if (phase == 0) {
            int selX = (i8 - 39) + 3;
            int selY = i5 + 3;
            if (px >= selX && px < selX + 26 && py >= selY && py < selY + 4 * 24) {
                int clickedType = (py - selY) / 24;
                if (clickedType >= 0 && clickedType < 4) {
                    if (selectedBuilding == clickedType && ((selectedBuilding <= unlockedBuildingTier && selectedBuilding <= highestTierUnlocked) || cheatActive)) {
                        launchDelay = 500;
                    } else {
                        selectedBuilding = clickedType;
                    }
                    return true;
                }
            }
        }
        int roofX = i8 - 37;
        int roofY = i5 + 140;
        if (px >= roofX - 5 && px <= roofX + 35 && py >= roofY && py <= roofY + 35) {
            if (cursorCol == -1) {
                keyFire = true;
            } else {
                cursorCol = -1;
                cursorRow = 4;
            }
            return true;
        }
        int gridX = i8 + 5;
        int gridY = i5 + 5;
        if (px >= gridX && px < gridX + 5 * 34 && py >= gridY && py < gridY + 5 * 34) {
            int col = (px - gridX) / 34;
            int row = (py - gridY) / 34;
            if (col >= 0 && col < 5 && row >= 0 && row < 5) {
                if (cursorCol == col && cursorRow == row) {
                    keyFire = true;
                } else {
                    cursorCol = col;
                    cursorRow = row;
                }
                return true;
            }
        }
        return false;
    }
}
