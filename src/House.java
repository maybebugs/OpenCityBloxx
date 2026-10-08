

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.Vector;
import jme.Command;
import jme.Font;
import jme.Graphics;
import jme.Image;

public class House extends GameMIDlet implements Screen {
    private static int weatherAreaWidth;
    private static int weatherAreaHeight;
    private static boolean vibrationOn = true;
    private static Image levelMedals;
    private static Image hudIcons;
    private static Image floorCounterFrame;
    private static Image lifeIcons;
    private static Image digitSheetB;
    private static Image digitSheetA;
    static final int[][] HIGH_SCORE_LAYOUT = new int[][]{new int[]{92, -1, -1}, new int[]{91, -1, -1}};
    private static int[] blockY = new int[5];
    private static int[] blockVelX = new int[5];
    private static int[] blockVelY = new int[5];
    private static int[] blockStartY = new int[5];
    private static int fallStartTime;
    private static byte[] landingOffsets = new byte[20];
    private static short[][] skylineSegments;
    private static int ropeLength = 1664;
    private static int pivotX;
    private static int pivotY;
    private static int hookX;
    private static int hookY;
    private static int swingAngle = 0;
    private static int prevHookX = 0;
    private static int prevHookY = 0;
    private static int craneTime = 0;
    private static final int[] swingAmplitudeXByType = new int[]{213, 256, 298, 341, 384};
    private static final int[] swingAmplitudeYByType = new int[]{85, 106, 128, 149, 170};
    private static final int[] swingPeriodTable = new int[]{1670, 1700, 1650, 1600, 1550, 1500, 1450};
    private static int centerX;
    private static int centerY;
    private static int camX;
    private static int camY;
    private static int camTargetY;
    private static int camEaseStart;
    private static int nextScreenState;
    private static Image citizenSpriteA;
    private static Image citizenSpriteB;
    private static Image cloudLargeSprite;
    private static Image cloudSmallSprite;
    private static Image fenceContainerSprite;
    private static Image fenceChainSprite;
    private static Image fenceWoodSprite;
    private static Image treeSprite;
    private static Image sparkSprite;
    private static Image arrowSprite;
    private static Image craneArmSprite;
    private static Image comboStarSprite;
    private static Image comboSparkleSprite;
    private static Image waterSplashSprite;
    private static int fenceHeight;
    private static int fenceContainerWidth;
    private static int fenceWoodWidth;
    private static int fenceChainWidth;
    private static int treeWidth;
    private static boolean inputSelect = false;
    private static boolean inputUp = false;
    private static boolean inputDown = false;
    private static int[] blockState = new int[5];
    private static int[] blockAngle = new int[5];
    private static int[] blockTargetAngle = new int[5];
    private static int[] blockX = new int[5];
    public static SoundPlayer soundPlayer;
    private static int comboTimer;
    private static int comboBonus;
    private static int maxCombo;
    private static boolean bonusEligible;
    private static int[][] citizens = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{8, 8}));
    private static int[] jumpArcY = new int[11];
    private static int[] jumpArcX = new int[11];
    private static short[][] skylineBounds;
    private static byte[][] skylineRects;
    private static int[] skylineColors;
    private static int skyColorLow;
    private static int skyColorHigh;
    private static int skyColorMid;
    private static int horizonBumpX;
    private static int[] skyPalette = new int[]{11720434, 10143978, 8436711, 6729700, 5344996, 4225726, 1858454, 802684, 1257578, 3416140, 3615825, 2968395, 4876098, 6768419, 5449523, 8399403, 5315119};
    private static int promptPageCount;
    private static int promptPage;
    private static int promptPaddingY;
    private static int promptPaddingX;
    private static int promptMarginX;
    private static int promptMarginY;
    private static int promptLinesPerPage;
    private static int promptLineHeight;
    private static int promptShownTime;
    private static String[] promptOptions;
    private static int promptSelection;
    private static int lives;
    private static int lastLossAmount;
    private static int lastLossTime;
    private static int viewWidthUnits = 0;
    private static int viewHeightUnits = 0;
    private static boolean newRecord;
    private static int blockGoal;
    private static int[] floorAngle = new int[5];
    private static int[] floorX = new int[5];
    private static int[] floorY = new int[5];
    private static int phase;
    private static int towerType;
    private static int offsetSum = 0;
    private static int scrolledOffsetSum = 0;
    private static int foundationOffset = 0;
    private static int unusedValue = 0;
    private static int landingQuality;
    private static int swayOffset;
    private static int blockCount;
    private static int score;
    private static int swayAngle;
    private static int swayAmplitude = 0;
    private static int swayPhase;
    private static int bottomFloor;
    private static int topFloorSlot;
    private static int comboCount;
    public static Vibra vibrator;
    private static Mesh3D topMeshBonus;
    private static Mesh3D fallingMesh;
    private static Mesh3D groundMesh;
    private static Mesh3D craneHookMesh;
    private static Mesh3D craneHookStaticMesh;
    private static int[] floorSway = new int[5];
    private static int[] blockTilt = new int[5];
    private static int[] blockTargetTilt = new int[5];
    private static int camZ;
    private static boolean cityInitPending;
    private static boolean blockLostThisFrame;
    private static int lastHitTime = 0;
    private static int craneYOffset;
    private static int swingPeriod;
    private static int swingAmplitudeX;
    private static int swingAmplitudeY;
    private static int swayCos;
    private static int landingBounce;
    private static int rainChance;
    private static int snowChance;
    private static int[][] weatherParams;
    private static boolean raining = false;
    private static boolean snowing = false;
    private static boolean reservedFlagA = false;
    private static boolean rainTriggered = false;
    private static Image promptImage;
    private static int promptImageHeight;
    private static int loadingProgress;
    private static int skyStageLength;
    private static Image[] flyerFrames;
    private static Random rng = new Random();
    private static int gameTime = 0;
    private static int lastPerfectTime;
    private static int[] sinTable;
    private static int[] progress = new int[6];
    private static final int[] slotDelays = new int[]{0, 150, 350, 550, 750};
    private static int[] slotDelay = new int[5];
    private static int[][] lostBlocks = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 5}));
    private static int hudX;
    private static int hudHeight;
    private static int skyStage;
    private static int backgroundTimer;
    private static int loadingScroll;
    private static int[] menuCitizens;
    private static int menuCitizenTimer;
    private static int[] menuClouds;
    private static int[] menuCloudInit;
    private static Image loadingImage;
    private static boolean cityInitialized;
    private static Mesh3D floorMesh;
    private static Mesh3D topMesh;
    public static int towersCompleted = 0;
    private static int[][] flyers = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{9, 6}));
    private static final int[] flyerImageIds = new int[]{42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, -1, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68};
    private static int floorIndex;
    private static int lastLandTime = 0;
    private static int towerInstability = 0;
    private static int[][] clouds;
    private static final int[] cloudColors = new int[]{7317456, 6070481, 5216461};
    private static int skylineStart;
    private static boolean snowTriggered = false;
    private static int[][] weatherEmitters = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 7}));
    private static int[][] weatherParticles = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{300, 7}));
    private static boolean lightingEnabled = false;
    private static boolean reservedFlagB = false;
    private static int lightLevel = 0;
    private static int lightBefore = 0;
    private static int lightTarget = 0;
    private static int lightFadeDuration = 0;
    private static int flashLevel = 0;
    private static int flashPeak = 0;
    private static int flashStart = 0;
    private static int flashTimer = 0;
    private static int flashDuration = 100;
    private static int reservedStateFlag = 0;
    private static int weatherPhase = 0;
    private static int phaseDuration = 0;
    private static int rampUpDuration = 0;
    private static int peakDuration = 0;
    private static int rampDownDuration = 0;
    private static int weatherTimer = 0;
    private static int weatherType = 2;
    private static int reservedValue = 0;
    private static int[] flyerCounts;
    private static final int[] flyerSizes = new int[28];
    private static final int[][] flyerTable = new int[][]{new int[]{0, 0, 0, 2, 3, 2, 3, 3, 4, 5, 6, 6, 6, 7, 8, 8, 9, 9, 10, 10, 11, 12, 12, 13, 14, 15, 17, 18, 21}, new int[]{1, 1, 1, 3, 4, 4, 4, 5, 5, 6, 7, 7, 7, 999, 9, 10, 999, 999, 11, 999, 12, 13, 14, 999, 15, 16, 18, 19, 999}, new int[]{0, 30, 30, 20, 20, 40, 60, 60, 30, 30, 50, 50, 10, 50, 100, 20, 40, 50, 100, 30, 100, 100, 30, 10, 100, 100, 100, 100, 100}, new int[]{60, 2, 1, 3, 2, 2, -2, 3, -4, 5, 2, 2, 6, 0, 0, -2, 0, 0, 0, 0, 0, 0, 3, -3, 0, 0, 0, 0, -3}};
    public static int gameMode;
    public static int screenState;
    static boolean roundFinishedQuick = false;
    static boolean roundFinishedCity = false;
    static boolean perfectTipActiveCity = false;
    static boolean perfectTipActiveQuick = false;
    public static final int[] goalByTowerType = new int[]{10, 20, 30, 40};
    public static int[] scoreThresholds;
    public static String[] promptLines;
    public static boolean loadCancelled = false;
    public static Font gameFont = Font.getFont(32, 0, 8);
    public static boolean hasQuickSave = false;
    public static boolean hasCitySave = false;
    private int loadingScreenType = -1;
    private int loadingTimeoutMs = -1;
    private boolean loadingKeyPressed = false;
    private Image splashImage = null;
    private Image titleLogoImage = null;
    private boolean loadingNeedsRepaint = false;
    private int pendingModeChange = 0;
    private Command softKeyCommand = null;
    private Image softKeyArrowImage = null;
    private boolean soundEnabled = true;
    private boolean titleBgmStarted = false;
    private int physicsAccumulatorMs;
    int skylineBandCount;
    int skylinePaletteSize;
    int skylineSegmentCount;

    static {
        House.buildCrcTable();
    }

    public House() {
        try {
            System.arraycopy(new byte[262144], 0, new byte[262144], 1, 261888);
        } catch (Throwable t) {
        }
        int i = 512;
        int i2 = 60000;
        int i3 = 307;
        int i4 = 50000;
        try {
            i = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Rain-Probability"));
        } catch (Exception e) {
        }
        try {
            i2 = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Rain-Duration")) * 1000;
        } catch (Exception e) {
        }
        try {
            i3 = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Snow-Probability"));
        } catch (Exception e3) {
        }
        try {
            i4 = Integer.parseInt(GameMIDlet.getInstance().getAppProperty("DChoc-Snow-Duration")) * 1000;
        } catch (Exception e4) {
        }
        rainChance = (i * 1024) / 100;
        snowChance = (i3 * 1024) / 100;
        int[][] weather = new int[2][];
        weather[0] = new int[]{i2 / 3, i2 / 30, i2 / 3, i2 / 12, i2 / 3, i2 / 30, 409, i2 / 3, 90, 1024, 32, 4, 5, 128, 16, 96, 0, 10, 256, 64};
        weather[1] = new int[]{i4 / 5, i4 / 25, (i4 * 3) / 5, i4 / 10, i4 / 5, i4 / 25, 819, i4 / 5, 90, 1024, 2, 0, 10, 50, 10, 2, 0, 10, 150, 32};
        weatherParams = weather;
        this.softKeyArrowImage = Resources.getImage(10);
        vibrator = this.vibra;
        soundPlayer = this.sound;
        this.sound.preload(-2147483568, false);
        this.sound.preload(-2147483567, false);
        this.sound.preload(-2147483566, false);
        this.sound.preload(-2147483565, false);
        this.sound.preload(-2147483564, false);
        this.sound.preload(-2147483563, false);
        Renderer3D.init(0, screenWidth, screenHeight);
        Renderer3D.preloadModels(new int[]{7, 8, 9, 10, 11, 12, 13, 20, 21, 22, 23, 30, 31, 32, 33, 40, 41, 42, 43}, -2147483558);
        weatherAreaWidth = screenWidth * 1024;
        weatherAreaHeight = screenHeight * 1024;
    }

    private static void updateCitizens() {
        int i = 0;
        while (i < 8) {
            int i2 = citizens[0][i];
            if (i2 != 0) {
                int i3 = 0;
                int i4 = gameTime - citizens[4][i];
                int i5;
                int i6;
                switch (i2) {
                    case 1:
                        i2 = i4 / 500;
                        i3 = i4 - (i2 * 500);
                        if (i2 > 5) {
                            i2 = 5;
                            i3 = 500;
                        }
                        i5 = citizens[1][i];
                        i6 = citizens[2][i];
                        int i7 = citizens[5][i];
                        citizens[7][i] = (i5 - ((jumpArcX[i2] * (i5 - citizens[9][i])) / 45)) - (((i5 - citizens[9][i]) * ((jumpArcX[i2 + 1] - jumpArcX[i2]) * i3)) / 22500);
                        citizens[8][i] = (i6 - ((jumpArcY[i2] * ((i6 - ((i7 - 1) * 256)) - 128)) / 15)) - (((i3 * (jumpArcY[i2 + 1] - jumpArcY[i2])) * ((i6 - ((i7 - 1) * 256)) - 128)) / 7500);
                        i2 = (((i4 - 1200) / 280) % 8) + 1;
                        if (i2 > 5) {
                            i2 = 5 - (i2 - 5);
                        }
                        if (i4 >= 2000) {
                            i3 = Math.min(4, blockCount - 1) - (blockCount - citizens[5][i]);
                            if (Math.abs(citizens[7][i] - floorX[i3]) < 128 && citizens[8][i] <= ((i7 - 1) * 256) + 128) {
                                citizens[0][i] = 2;
                                i4 = citizens[7][i] - floorX[i3];
                                i3 = (((i7 - 1) * 256) + 128) - citizens[8][i];
                                citizens[1][i] = i4;
                                citizens[2][i] = i3;
                                citizens[4][i] = gameTime;
                                i3 = 1;
                                if (i4 < 0) {
                                    i3 = -1;
                                }
                                citizens[9][i] = i3;
                                citizens[6][i] = 1;
                                i3 = i2;
                                break;
                            }
                            i3 = i2;
                            break;
                        }
                        i3 = i2;
                        break;
                    case 2:
                        i3 = Math.min(4, blockCount - 1) - (blockCount - citizens[5][i]);
                        i2 = citizens[1][i];
                        i5 = citizens[2][i];
                        i6 = citizens[9][i];
                        citizens[7][i] = (i2 + floorX[i3]) - ((i6 * i4) / 12);
                        if (Math.abs(citizens[7][i] - floorX[i3]) < 64) {
                            citizens[7][i] = floorX[i3] + (i6 * 64);
                        }
                        citizens[8][i] = Math.min(floorY[i3], (i4 / 12) + (floorY[i3] - i5));
                        if (citizens[7][i] == floorX[i3] + (i6 * 64) && citizens[8][i] == floorY[i3]) {
                            citizens[1][i] = i6 * 64;
                            citizens[0][i] = 5;
                            citizens[4][i] = gameTime;
                        }
                        i3 = ((gameTime / 200) % 2) + 6;
                        break;
                    case 3:
                        i3 = citizens[5][i];
                        citizens[7][i] = citizens[1][i] - ((i3 * i4) / 30);
                        citizens[8][i] = citizens[2][i] - ((Math.abs(10 - i3) * i4) / 30);
                        i3 = ((i4 / 280) % 8) + 1;
                        if (i3 > 5) {
                            i3 = 5 - (i3 - 5);
                            break;
                        }
                        break;
                    case 4:
                        i3 = citizens[5][i];
                        citizens[7][i] = citizens[1][i] - (((citizens[6][i] * (10 - i3)) * i4) / 15);
                        citizens[8][i] = (((i3 * citizens[6][i]) * i4) / 15) + citizens[2][i];
                        if (i4 > 300) {
                            citizens[1][i] = citizens[7][i];
                            citizens[2][i] = citizens[8][i];
                            citizens[0][i] = 3;
                            citizens[4][i] = gameTime;
                        }
                        i3 = 0;
                        break;
                    case 5:
                        i3 = Math.min(4, blockCount - 1) - (blockCount - citizens[5][i]);
                        citizens[7][i] = floorX[i3] + citizens[1][i];
                        citizens[8][i] = floorY[i3];
                        if (i4 > 500) {
                            citizens[0][i] = 0;
                        }
                        i3 = 7;
                        break;
                }
                if (citizens[8][i] < camY - (viewHeightUnits >> 1) || citizens[7][i] < (camX - (viewWidthUnits >> 1)) - 256 || citizens[7][i] > (camX + (viewWidthUnits >> 1)) + 256) {
                    citizens[0][i] = 0;
                }
                citizens[3][i] = i3;
            }
            i++;
        }
    }

    private static void loseLives(int count) {
        lastHitTime = gameTime;
        vibrator.vibrate(800);
        lastLossAmount = count;
        lives -= count;
        lastLossTime = gameTime;
        if (lives == 0 && !CityMode.isCheatActive()) {
            phase = PHASE_ROUND_OVER;
        }
    }

    private static void clearWeather() {
        int i;
        for (i = 0; i < 2; i++) {
            House.spawnEffect(i, 0, 0, 0, 0, 0, 0);
        }
        for (i = 0; i < 300; i++) {
            int[] iArr = weatherParticles[i];
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            iArr[5] = 0;
            iArr[6] = 0;
        }
    }

    private static void spawnWeatherParticles() {
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            int[] iArr = weatherEmitters[i2];
            int i3 = iArr[5] + 1;
            int i4 = iArr[6] + 1;
            int i5 = iArr[1] >> 10;
            for (int i6 = 0; i6 < i5; i6++) {
                int[] iArr2 = weatherParticles[i6 + i];
                iArr2[0] = 1;
                iArr2[1] = House.random(weatherAreaWidth);
                iArr2[2] = House.random(weatherAreaHeight);
                iArr2[3] = House.random(4);
                iArr2[4] = House.random(368640);
                iArr2[5] = ((iArr[3] + House.random(i3)) - (i3 / 2)) / (4 - iArr2[3]);
                iArr2[6] = ((iArr[4] + House.random(i4)) - (i4 / 2)) / (4 - iArr2[3]);
            }
            iArr[2] = 0;
            i += iArr[0];
        }
    }

    private static void updateFlyers() {
        int i = 0;
        while (i < 9) {
            int[] iArr = flyers[i];
            int i2 = iArr[0];
            if (i2 != 0) {
                iArr = flyers[i];
                iArr[1] = iArr[1] + flyerTable[3][i2];
                if (flyers[i][1] > viewWidthUnits + flyerSizes[i2 - 1] || flyers[i][1] < (-viewWidthUnits) - flyerSizes[i2 - 1] || ((camY * 3) / 4) - flyers[i][2] > viewHeightUnits + flyerSizes[i2 - 1]) {
                    flyers[i][0] = 0;
                    flyers[i][3] = gameTime + House.random(2000);
                    if (flyerCounts[i2] >= 0) {
                        iArr = flyerCounts;
                        iArr[i2] = iArr[i2] + 1;
                    }
                }
            } else if (iArr[3] < gameTime) {
                House.spawnFlyer(i);
            }
            i++;
        }
    }

    private static int findLandingFloor() {
        int i = -1;
        do {
            if (blockY[0] < floorY[floorIndex]) {
                floorIndex = Math.max(0, floorIndex - 1);
            }
            if (blockY[0] >= floorY[floorIndex] + 256 || blockY[0] <= floorY[floorIndex] - 256 || blockX[0] >= floorX[floorIndex] + 256 || blockX[0] <= floorX[floorIndex] - 256) {
                return i;
            }
            i = floorIndex;
            floorIndex--;
        } while (floorIndex >= 0);
        return i;
    }

    /**
     * Called when the swinging block is dropped.
     * Decides whether the block lands on the tower, how well (offset / perfect placement),
     * combo counting and the bonus/score for the floor. Rewritten from bytecode.
     *
     * @return true when the drop was handled as a landing, false otherwise
     */
    private static boolean handleLanding() {
        int slot = House.findLandingFloor();
        if (phase == PHASE_ROOF && floorIndex != 4 && slot != 4) {
            landingQuality = 0;
            blockState[0] = BLOCK_RETURNING;
            House.loseLives(1);
            return false;
        }
        if (slot == -1) {
            return false;
        }
        fallStartTime = gameTime;
        int offset = blockX[0] - floorX[slot];
        int abs = Math.abs(offset);
        if (blockCount == 0) {
            // first floor: foundation, no scoring
            lastLandTime = gameTime;
            lastHitTime = gameTime;
            vibrator.vibrate(800);
            blockState[0] = BLOCK_LANDED;
            blockAngle[0] = 0;
            foundationOffset = offset;
            scrolledOffsetSum = offset;
            offsetSum = offset;
            House.changeFloorCount(1);
            return true;
        }
        if (slot == Math.min(4, blockCount - 1)) {
            House.nudgeSwayPhase(offset);
            if (offset > 127 || offset < -127) {
                // missed: the block falls off
                if (comboTimer > 0) {
                    House.cashInCombo();
                }
                if (phase == PHASE_ROOF) {
                    landingQuality = 0;
                    blockState[0] = BLOCK_RETURNING;
                    House.loseLives(1);
                    return false;
                }
                blockState[0] = BLOCK_TUMBLING;
                int direction = offset / Math.abs(offset);
                House.toppleBlocks(direction, -1);
                blockVelX[0] = direction * 500;
                blockStartY[0] = blockY[0];
                blockTargetAngle[0] = (-direction) * 45;
                blockTargetTilt[0] = (1 - (House.random(2) * 2)) * 60;
                fallStartTime = gameTime;
                blockVelY[0] = 50;
                return false;
            }
            // landed on the tower
            blockState[0] = BLOCK_LANDED;
            lastLandTime = gameTime;
            if (comboCount == 0) {
                comboBonus = 0;
                comboCount++;
            } else if (comboTimer > 0) {
                comboCount++;
            }
            if (comboCount > 1) {
                maxCombo = Math.max(maxCombo, comboCount);
            }
            offsetSum += offset;
            if (abs < 25) {
                // perfect placement
                if (progress[2] == 0 && !House.perfectTipActiveCity && !perfectTipActiveQuick) {
                    if (gameMode == GAME_MODE_CITY_TOWER) {
                        House.perfectTipActiveCity = true;
                    } else if (gameMode == GAME_MODE_QUICK) {
                        perfectTipActiveQuick = true;
                    }
                    House.showPrompt(Resources.getString(35), null, null);
                    screenState = 8;
                }
                landingOffsets[blockCount % 20] = (byte) 0;
                if (phase != PHASE_ROOF) {
                    comboTimer = 6000;
                }
                House.spawnCitizens(4, blockCount + 1);
                lastPerfectTime = gameTime;
            } else {
                landingOffsets[blockCount % 20] = (byte) offset;
                House.spawnCitizens(abs < 50 ? 3 : abs < 80 ? 2 : 1, blockCount + 1);
            }
            for (int k = 1; k < 5; k++) {
                floorSway[k - 1] = floorSway[k];
            }
            floorSway[slot] = offset / 4;
            if (House.random(2) == 0) {
                floorSway[slot] = -floorSway[slot];
            }
            House.changeFloorCount(1);
            if (phase == PHASE_ROOF) {
                if (bonusEligible) {
                    comboBonus = ((128 - abs) * towerType) / 2;
                    landingQuality = 2;
                } else {
                    comboBonus = (128 - abs) / (5 - towerType);
                    landingQuality = 1;
                }
                House.addScore(comboBonus);
                comboTimer = 0;
            }
            return true;
        }
        // landed on a lower slot than the current top: the block topples over
        int direction = offset / abs;
        House.toppleBlocks(-direction, (Math.min(4, blockCount - 1) - slot) + 1);
        if (comboTimer > 0) {
            House.cashInCombo();
        }
        blockState[0] = BLOCK_TUMBLING;
        blockVelX[0] = direction * 500;
        blockStartY[0] = blockY[0];
        blockTargetAngle[0] = (-direction) * 45;
        fallStartTime = gameTime;
        blockVelY[0] = 50;
        return true;
    }

    private static void cashInCombo() {
        score += comboBonus;
        comboCount = 0;
        comboTimer = 0;
    }

    private static void clearKeys() {
        inputSelect = false;
        inputUp = false;
        inputDown = false;
    }

    private void handleInput() {
        int i = 0;
        int i2 = 0;
        if (!inputSelect) {
            if (inputDown) {
                if ((screenState == 4 || screenState == 5 || screenState == 6 || screenState == 8) && House.handleModalAction(1)) {
                    i = 1;
                }
            } else if (inputUp) {
                House.handleModalAction(-1);
            }
        } else if ((screenState == 4 || screenState == 5 || screenState == 6 || screenState == 8) && House.handleModalAction(0)) {
            i = 1;
        } else if ((screenState == 1 || screenState == 3) && blockState[0] == BLOCK_HANGING) {
            camY = camTargetY;
            blockState[0] = BLOCK_DROPPED;
            blockStartY[0] = hookY;
            blockTargetAngle[0] = 0;
            fallStartTime = gameTime;
            House.clearKeys();
        }
        if (i != 0) {
            if (screenState == 4) {
                int[] iArr;
                if (gameMode == GAME_MODE_CITY_TOWER) {
                    iArr = progress;
                } else {
                    iArr = progress;
                    i2 = 1;
                }
                iArr[i2] = 1;
                saveSettings();
                screenState = 1;
            } else if (screenState == 5) {
                if (roundFinishedCity) {
                    roundFinishedCity = false;
                }
                if (roundFinishedQuick) {
                    roundFinishedQuick = false;
                }
                buildResultText(false);
                if (gameMode == GAME_MODE_QUICK) {
                    clearSavedGame(1);
                }
            } else if (screenState == 6) {
                if (roundFinishedCity) {
                    roundFinishedCity = false;
                }
                if (roundFinishedQuick) {
                    roundFinishedQuick = false;
                }
                if (gameMode == GAME_MODE_CITY_TOWER) {
                    CityMode.startPlacement(towerType, score, landingQuality);
                    screenState = 7;
                    nextScreenState = 2;
                } else {
                    this.soundEnabled = false;
                    this.pendingModeChange = 3;
                    House.saveTowerQuickMode();
                }
                if (gameMode == GAME_MODE_QUICK) {
                    clearSavedGame(1);
                }
            } else if (screenState == 8) {
                screenState = 1;
                progress[2] = 1;
                saveSettings();
                if (gameMode == GAME_MODE_CITY_TOWER) {
                    House.perfectTipActiveCity = false;
                } else if (gameMode == GAME_MODE_QUICK) {
                    perfectTipActiveQuick = false;
                }
            }
        }
        House.clearKeys();
    }

    private static void initArrays() {
        boolean z = true;
        blockState = new int[5];
        blockAngle = new int[5];
        blockTargetAngle = new int[5];
        blockX = new int[5];
        blockY = new int[5];
        blockVelX = new int[5];
        blockVelY = new int[5];
        blockStartY = new int[5];
        gameTime = 0;
        landingOffsets = new byte[20];
        blockCount = 0;
        foundationOffset = 0;
        unusedValue = 0;
        scrolledOffsetSum = 0;
        pivotX = 0;
        pivotY = 2432;
        floorAngle = new int[5];
        floorX = new int[5];
        floorY = new int[5];
        floorY[0] = -256;
        hookX = 0;
        camX = pivotX;
        camY = pivotY;
        blockState[0] = BLOCK_HANGING;
        ropeLength = 1664;
        camTargetY = 512;
        camEaseStart = gameTime + 3000;
        blockY[0] = pivotY - ropeLength;
        hookY = pivotY - ropeLength;
        floorSway = new int[5];
        blockTilt = new int[5];
        blockTargetTilt = new int[5];
        swayOffset = 0;
        offsetSum = 0;
        swayAmplitude = 0;
        swayPhase = 0;
        gameTime = 0;
        score = 0;
        comboTimer = -2000;
        lives = 3;
        landingQuality = 0;
        floorIndex = 1;
        citizens = (int[][]) Array.newInstance(Integer.TYPE, new int[]{11, 8});
        craneTime = 2000;
        swingPeriod = swingPeriodTable[towerType];
        swingAmplitudeX = 128;
        swingAmplitudeY = 64;
        craneYOffset = 0;
        lastPerfectTime = gameTime - 600;
        skyStage = -1;
        lastLandTime = gameTime - 100;
        House.initClouds();
        skylineStart = 0;
        comboBonus = 0;
        phase = PHASE_INTRO;
        maxCombo = 0;
        lastHitTime = gameTime - 800;
        House.clearKeys();
        raining = rainChance >= House.random(1024);
        snowing = snowChance >= House.random(1024);
        if (1024 < House.random(1024)) {
            z = false;
        }
        reservedFlagA = z;
        rainTriggered = false;
        snowTriggered = false;
        weatherPhase = 0;
        lightingEnabled = false;
        reservedFlagB = false;
        House.clearWeather();
        flyers = (int[][]) Array.newInstance(Integer.TYPE, new int[]{9, 6});
        flyerCounts = new int[]{8, 3, 2, 1, 1, 8, 2, 8, 1, 1, 4, 4, -1, 8, -1, 1, 5, 4, -1, 5, 2, -1, 1, 1, -1, -1, -1, -1, -1};
        lostBlocks = (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 5});
    }

    private static void initClouds() {
        clouds = (int[][]) Array.newInstance(Integer.TYPE, new int[]{12, 7});
        int i = screenWidth / 12;
        for (int i2 = 0; i2 < 12; i2++) {
            int i3 = House.random(8) + 8;
            clouds[i2][1] = i3;
            clouds[i2][0] = (i2 * i) - House.random(i3);
            clouds[i2][2] = House.random(256) + 640;
            clouds[i2][3] = House.random(16) + 16;
            clouds[i2][4] = 1;
            clouds[i2][5] = cloudColors[House.random(3)];
            clouds[i2][6] = House.random(3);
        }
    }

    private static void initSinTable() {
        int i = 0;
        int i2 = 32768;
        long j = ((((long) 32768) * 31416) * 2) / 3600000;
        sinTable = new int[360];
        int i3 = 0;
        while (i < 360) {
            sinTable[i] = i3;
            i2 = (int) (((long) i2) - ((((long) i3) * j) >> 15));
            i3 = (int) (((long) i3) + ((((long) i2) * j) >> 15));
            i++;
        }
    }

    private static int[] buildCrcTable() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (i2 & 1) == 0 ? i2 >>> 1 : (i2 >>> 1) ^ -306674912;
            }
            iArr[i] = i2;
        }
        return iArr;
    }

    private static void resetPrompt() {
        promptPageCount = 0;
        promptPage = 0;
        promptShownTime = 0;
        promptOptions = null;
        promptImage = null;
        promptImageHeight = 0;
    }

    private static boolean isLastPromptPage() {
        return promptPage == promptPageCount + -1;
    }

    private static int shiftDecay(int value, int target, int decayRate) {
        return value >> (target / decayRate);
    }

    private static int interpolate(int a, int b, int numerator, int denominator, int minValue) {
        return b - a == 0 ? 0 : (((denominator - numerator) * minValue) / (b - a)) + numerator;
    }

    private static int shadeColor(int color, int shadeLevel, boolean isDark) {
        if (!lightingEnabled || !isDark) {
            return color;
        }
        int i3 = (((65280 & color) >> 8) * shadeLevel) >> 10;
        return ((Math.max(0, Math.min((((16711680 & color) >> 16) * shadeLevel) >> 10, 255)) << 16) | (Math.max(0, Math.min(i3, 255)) << 8)) | Math.max(0, Math.min((((color & 255) >> 0) * shadeLevel) >> 10, 255));
    }

    private static void spawnEffect(int effectType, int x, int y, int velX, int velY, int lifeTime, int count) {
        int[] iArr = weatherEmitters[effectType];
        iArr[0] = x;
        iArr[1] = y;
        iArr[2] = 0;
        iArr[3] = velX;
        iArr[4] = velY;
        iArr[5] = lifeTime;
        iArr[6] = count;
    }

    public static void showPrompt(String promptText, String[] options, Image promptIcon) {
        House.resetPrompt();
        promptLines = null;
        if (promptText != null) {
            promptLines = House.wrapText(promptText, gameFont, screenWidth - ((promptMarginX + promptPaddingX) << 1));
            int length = promptLines.length;
            promptLineHeight = gameFont.getHeight() + 2;
            promptLinesPerPage = (screenHeight - ((promptMarginY + promptPaddingY) << 1)) / promptLineHeight;
            promptPageCount = length / promptLinesPerPage;
            if (length % promptLinesPerPage > 0) {
                promptPageCount++;
            }
            if (options != null) {
                if ((length - ((promptPageCount - 1) * promptLinesPerPage)) + options.length > promptLinesPerPage) {
                    promptPageCount++;
                }
                promptOptions = options;
            }
            if (promptIcon != null) {
                promptImage = promptIcon;
                promptImageHeight = promptImage.getHeight();
                if (screenHeight - ((promptMarginY + promptPaddingY) << 1) < ((length - ((promptPageCount - 1) * promptLinesPerPage)) * promptLineHeight) + promptImageHeight) {
                    promptPageCount++;
                }
            }
            promptShownTime = gameTime;
        }
    }

    private static void paintSkyline(Graphics g, int baseY) {
        int i2 = skylineStart;
        while (i2 < skylineRects.length) {
            short s = skylineBounds[i2][1];
            int i3 = ((centerY - skylineBounds[i2][0]) - s) + baseY;
            if (i3 + s >= 0) {
                if (i3 <= screenHeight) {
                    g.setColor(House.shadeColor(skylineColors[skylineRects[i2][2]], lightLevel, true));
                    g.fillRect(skylineRects[i2][0] & 255, i3, skylineRects[i2][1] & 255, s);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private static void drawSnowflake(Graphics g, int x, int y, int size) {
        if (size > 1) {
            g.setColor(14540287);
            g.fillRect(x + 1, y + 1, size - 1, size - 1);
            g.drawLine(x, y, x, y);
            g.drawLine(x + size, y, x + size, y);
            g.drawLine(x, y + size, x, y + size);
            g.drawLine(x + size, y + size, x + size, y + size);
            return;
        }
        g.setColor(11184861);
        g.fillRect(x, y, size + 1, size + 1);
    }

    public static void setClipRect(Graphics g, int x, int y, int width, int height) {
        g.setClip(x, y, width, height);
    }

    private static void drawRainDrop(Graphics g, int startX, int startY, int endX, int endY, int color) {
        int i6;
        int i7;
        if (color < 0) {
            g.setColor(House.shadeColor(11184844, (lightLevel / 2) + 512, true));
            g.drawLine(startX, startY, endX + startX, endY + startY);
        }
        if (color < 2) {
            g.setColor(House.shadeColor(11184844, (lightLevel / 4) + 768, true));
            i6 = startY;
            i7 = startX;
        } else {
            g.setColor(11184844);
            g.drawLine(startX, startY, endX + startX, endY + startY);
            g.setColor(16777215);
            i7 = endX + startX;
            i6 = endY + startY;
        }
        g.drawLine(i7, i6, endX + startX, endY + startY);
    }

    private static void drawDigitsA(Graphics g, int value, int x, int y, int anchor, boolean isGoldSheet) {
        int i5 = 0;
        int i6 = x - 6;
        while (true) {
            if (value <= 0 && i5 >= anchor) {
                break;
            }
            House.setClipRect(g, i6, y, 7, 12);
            int i7 = value % 10;
            value /= 10;
            g.drawImage(digitSheetA, i6 - (i7 * 7), y, 20);
            i6 -= 6;
            i5++;
        }
        if (isGoldSheet) {
            House.setClipRect(g, i6, y, 7, 12);
            g.drawImage(digitSheetA, i6 - 70, y, 20);
        }
    }

    private static void paintSky(Graphics g, int scrollOffset, boolean withSun, boolean withStars) {
        int i2 = ((((scrollOffset * 2) / 3) % skyStageLength) * 32) >> 8;
        int i3 = ((scrollOffset * 2) / 3) / skyStageLength;
        if (i3 != skyStage) {
            int[] iArr;
            skyStage = i3;
            if (withSun) {
                skyColorLow = skyPalette[(i3 % 3) + 1];
                iArr = skyPalette;
                i3 = ((i3 + 1) % 3) + 1;
            } else {
                skyColorLow = skyPalette[Math.min(skyStage, Math.max(9, ((skyStage - 9) % 8) + 9))];
                iArr = skyPalette;
                i3 = Math.min(skyStage + 1, Math.max(9, (((skyStage + 1) - 9) % 8) + 9));
            }
            skyColorHigh = iArr[i3];
            skyColorMid = ((((((skyColorLow & -65536) >> 16) + ((skyColorHigh & -65536) >> 16)) >> 1) << 16) + (((((skyColorLow & -16711936) >> 8) + ((skyColorHigh & -16711936) >> 8)) >> 1) << 8)) + (((skyColorLow & -16776961) + (skyColorHigh & -16776961)) >> 1);
            horizonBumpX = (screenWidth >> 2) + House.random(screenWidth >> 1);
        }
        if (i2 < screenHeight) {
            g.setColor(House.shadeColor(skyColorLow, lightLevel + flashLevel, withStars));
            g.fillRect(0, i2, screenWidth, screenHeight - i2);
        }
        if (i2 > 0) {
            g.setColor(House.shadeColor(skyColorHigh, lightLevel + flashLevel, withStars));
            g.fillRect(0, 0, screenWidth, i2);
        }
        g.setColor(House.shadeColor(skyColorMid, lightLevel + flashLevel, withStars));
        g.fillRect(0, i2 - 7, horizonBumpX, 7);
        g.fillRect(horizonBumpX, i2 - 1, screenWidth - horizonBumpX, 7);
        g.fillRect(horizonBumpX - 24, i2 - 2, 48, 3);
        g.fillRect(horizonBumpX - 18, i2 - 3, 36, 5);
        g.fillRect(horizonBumpX - 16, i2 - 5, 32, 9);
        g.fillRect(horizonBumpX - 15, i2 - 6, 30, 11);
        g.fillRect(horizonBumpX - 13, i2 - 7, 26, 13);
    }

    private void buildResultText(boolean isVictory) {
        String stringBuffer = new StringBuffer().append(Resources.getString(93, new String[]{new StringBuffer().append("").append(score).toString()})).append('\n').toString();
        String bonusText = Resources.getString(96);
        if (isVictory && score > progress[3]) {
            newRecord = true;
            stringBuffer = new StringBuffer().append(stringBuffer).append(bonusText).append('\n').toString();
            progress[3] = score;
        }
        stringBuffer = new StringBuffer().append(stringBuffer).append(Resources.getString(94, new String[]{new StringBuffer().append("").append(blockCount).toString()})).append('\n').toString();
        if (isVictory && blockCount > progress[4]) {
            newRecord = true;
            stringBuffer = new StringBuffer().append(stringBuffer).append(bonusText).append('\n').toString();
            progress[4] = blockCount;
        }
        stringBuffer = new StringBuffer().append(stringBuffer).append(Resources.getString(95, new String[]{new StringBuffer().append("").append(maxCombo).toString()})).toString();
        if (isVictory && maxCombo > progress[5]) {
            stringBuffer = new StringBuffer().append(stringBuffer).append('\n').append(bonusText).toString();
            progress[5] = maxCombo;
        }
        saveSettings();
        House.showPrompt(stringBuffer, null, null);
        screenState = 6;
        phase = PHASE_RESULT;
        if (gameMode == GAME_MODE_CITY_TOWER) {
            roundFinishedCity = true;
        } else if (gameMode == GAME_MODE_QUICK) {
            roundFinishedQuick = true;
        }
        if (gameMode == GAME_MODE_QUICK && !CityMode.isCheatActive()) {
            this.highScores.submitScore(1, new int[]{score, blockCount}, null);
        }
        if (gameMode == GAME_MODE_CITY_TOWER) {
            towersCompleted++;
        }
    }

    public static boolean hasSavedGame(int saveSlotOrGameMode) {
        return saveSlotOrGameMode == 3 ? Storage.getMenuFlag(8) == 0 : saveSlotOrGameMode == 1 ? Storage.getMenuFlag(9) == 0 : false;
    }

    /** Character-by-character word wrap into lines of at most maxWidth pixels. */
    public static String[] wrapText(String text, Font font, int maxWidth) {
        if (text == null) {
            return new String[0];
        }
        int lineStart = 0;
        int pos = 0;
        int newline = 0;
        int length = text.length();
        Vector lines = new Vector();
        while (newline < length) {
            newline = text.indexOf(10, lineStart);
            if (newline == -1) {
                newline = length;
            }
            boolean done = false;
            while (!done) {
                pos = lineStart;
                int width = 0;
                int lastSpace = -1;
                while (width < maxWidth && pos < newline) {
                    char c = text.charAt(pos);
                    width += font.stringWidth(new StringBuffer().append("").append(c).toString());
                    pos++;
                    if (c == ' ') {
                        lastSpace = pos;
                    }
                }
                if (pos == newline && width <= maxWidth) {
                    done = true;
                } else if (lastSpace != -1) {
                    pos = lastSpace;
                } else {
                    pos--;
                }
                lines.addElement(text.substring(lineStart, pos));
                if (done && pos < length) {
                    pos++;
                }
                lineStart = pos;
            }
        }
        String[] result = new String[lines.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new String(((String) lines.elementAt(i)).trim());
        }
        return result;
    }

    private static void renderBlock(int blockSlot, int x, int y, int angle, int blockType) {
        int i6 = 0;
        if (x >= -256) {
            Mesh3D dVar;
            if (angle == 888) {
                dVar = bonusEligible ? topMeshBonus : topMesh;
            } else {
                if (angle == 999) {
                    dVar = fallingMesh;
                    angle = 0;
                } else {
                    dVar = floorMesh;
                }
                i6 = angle;
            }
            dVar.resetTransform();
            dVar.translate((float) blockSlot, (float) x, (float) y);
            dVar.rotate(((float) i6) / 360.0f, 0.0f, 0.0f, 1.0f);
            dVar.rotate(((float) blockType) / 360.0f, 0.0f, 1.0f, 0.0f);
            dVar.render();
        }
    }

    private static void drawSparks(Graphics g, int x, int y, int count) {
        int[] iArr = new int[]{512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        int[] iArr2 = new int[]{6885390, 12152341, 14540064, 15658544, 16777008, 16777088, 16777215, 16777215, 16777215, 16777215};
        House.random(10);
        int i4 = (count * 4) + 4;
        int i5 = (count * 4) + 4;
        for (int i6 = 0; i6 < 8; i6++) {
            int i7 = House.random(i4);
            int i8 = House.random(i5);
            g.setColor(iArr2[i6]);
            g.fillRect(i7 + x, i8 + y, count + 1, count + 1);
        }
    }

    private static void drawCitizen(Graphics g, int citizenIdx, int x, int y, int frame, int citizenType) {
        House.setClipRect(g, citizenIdx, x, 21, 28);
        Image image = citizenType == 1 ? citizenSpriteA : citizenSpriteB;
        if (frame < 0) {
            g.drawImageFlipped(image, citizenIdx - ((9 - y) * 21), x, 20);
            return;
        }
        int i6;
        if (frame < 0) {
            i6 = citizenIdx - ((7 - y) * 21);
            x -= 28;
        } else {
            i6 = citizenIdx - (y * 21);
        }
        g.drawImage(image, i6, x, 20);
    }

    private static void drawDigitsB(Graphics g, int value, int x, int y, int anchor, boolean isGoldSheet) {
        int i5 = 0;
        int i6 = x - 6;
        while (true) {
            if (value <= 0 && i5 >= anchor) {
                break;
            }
            House.setClipRect(g, i6, y, 7, 9);
            int i7 = value % 10;
            value /= 10;
            g.drawImage(digitSheetB, i6 - (i7 * 7), y, 20);
            i6 -= 6;
            i5++;
        }
        if (isGoldSheet) {
            House.setClipRect(g, i6, y, 7, 9);
            g.drawImage(digitSheetB, i6 - 70, y, 20);
        }
    }

    private static void paintWeatherLayer(Graphics g, boolean foreground) {
        int[][] iArr;
        int i;
        int i2 = foreground ? weatherEmitters[0][0] : 0;
        if (foreground) {
            iArr = weatherEmitters;
            i = 1;
        } else {
            iArr = weatherEmitters;
            i = 0;
        }
        int[] iArr2 = iArr[i];
        for (int i3 = 0; i3 < iArr2[0]; i3++) {
            int[] iArr3 = weatherParticles[i3 + i2];
            if (iArr3[0] == 1) {
                switch (weatherType) {
                    case 1:
                        House.drawRainDrop(g, iArr3[1] >> 10, iArr3[2] >> 10, (iArr3[5] * 32) >> 10, (iArr3[6] * 32) >> 10, foreground ? iArr3[3] : -1);
                        break;
                    case 2:
                        House.drawSnowflake(g, iArr3[1] >> 10, iArr3[2] >> 10, iArr3[3] >> (foreground ? 0 : 1));
                        break;
                    case 3:
                        House.drawSparks(g, iArr3[1] >> 10, iArr3[2] >> 10, 0);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void spawnCitizens(int floorSlot, int count) {
        if (phase != PHASE_ROOF) {
            House.addScore(floorSlot);
            int i3 = floorSlot >> 1;
            for (int i4 = 0; i4 < 8 && i3 > 0; i4++) {
                if (citizens[0][i4] == 0) {
                    citizens[0][i4] = 1;
                    citizens[5][i4] = count;
                    int i5 = 1 - (House.random(2) * 2);
                    citizens[1][i4] = camX + (((viewWidthUnits >> 1) + 256) * i5);
                    citizens[9][i4] = offsetSum;
                    int i6 = House.random(1000);
                    citizens[2][i4] = (camY + (viewHeightUnits >> 1)) + House.random(768);
                    citizens[4][i4] = gameTime - i6;
                    citizens[10][i4] = House.random(2);
                    citizens[6][i4] = -i5;
                    i3--;
                }
            }
        }
    }

    public static void paintTutorialModal(Graphics g) {
        int i;
        int i2 = 0;
        int i3 = screenHeight - ((promptMarginX + promptPaddingY) << 1);
        int min = Math.min(i3, ((gameTime - promptShownTime) * i3) / 600);
        House.setClipRect(g, 0, 0, screenWidth, screenHeight);
        g.setColor(8220270);
        g.drawRect(promptMarginX, promptMarginY + (i3 - min), screenWidth - (promptMarginX << 1), (screenHeight - (promptMarginY << 1)) - (i3 - min));
        g.setColor(15458785);
        g.drawRect(promptMarginX + 1, (promptMarginY + 1) + (i3 - min), screenWidth - ((promptMarginX + 1) << 1), (screenHeight - ((promptMarginY + 1) << 1)) - (i3 - min));
        g.drawRect(promptMarginX + 2, (promptMarginY + 2) + (i3 - min), screenWidth - ((promptMarginX + 2) << 1), (screenHeight - ((promptMarginY + 2) << 1)) - (i3 - min));
        g.setColor(9524565);
        g.drawRect(promptMarginX + 3, (promptMarginY + 3) + (i3 - min), screenWidth - ((promptMarginX + 3) << 1), (screenHeight - ((promptMarginY + 3) << 1)) - (i3 - min));
        g.setColor(16777215);
        g.fillRect(promptMarginX + 4, (promptMarginY + 4) + (i3 - min), (screenWidth - ((promptMarginX + 4) << 1)) + 1, ((screenHeight - ((promptMarginY + 4) << 1)) + 1) - (i3 - min));
        g.setColor(1313804);
        g.drawLine((screenWidth - promptMarginX) + 1, (promptMarginY + (i3 - min)) + 1, (screenWidth - promptMarginX) + 1, screenHeight - promptMarginY);
        g.drawLine(promptMarginX + 1, (screenHeight - promptMarginY) + 1, (screenWidth - promptMarginX) + 1, (screenHeight - promptMarginY) + 1);
        House.setClipRect(g, 0, (promptMarginY + 4) + (i3 - min), screenWidth, ((screenHeight - ((promptMarginY + 4) << 1)) + 1) - ((i3 - min) + 18));
        for (i = 0; i < promptLinesPerPage; i++) {
            if ((promptPage * promptLinesPerPage) + i < promptLines.length) {
                g.drawString(promptLines[(promptPage * promptLinesPerPage) + i], screenWidth >> 1, ((promptMarginY + promptPaddingY) + (promptLineHeight * i)) + (i3 - min), 17);
            }
        }
        if (House.isLastPromptPage() && promptOptions != null) {
            i = Math.max(0, promptLines.length - (promptPage * promptLinesPerPage));
            g.setColor(16266752);
            g.drawRect((promptMarginX + promptPaddingX) + 4, ((promptMarginY + promptPaddingY) + ((promptSelection + i) * promptLineHeight)) + (i3 - min), screenWidth - (((promptMarginX + 4) + promptPaddingX) << 1), promptLineHeight);
            while (i2 < promptOptions.length) {
                g.drawString(promptOptions[i2], screenWidth >> 1, ((promptMarginY + promptPaddingY) + ((i + i2) * promptLineHeight)) + (i3 - min), 17);
                i2++;
            }
        } else if (House.isLastPromptPage() && promptImage != null) {
            i = Math.max(0, promptLines.length - (promptPage * promptLinesPerPage));
            g.drawImage(promptImage, screenWidth >> 1, ((i * promptLineHeight) + (promptMarginY + promptPaddingY)) + (i3 - min), 17);
        }
        if (gameTime - promptShownTime >= 600) {
            i = 1;
            if (House.isLastPromptPage()) {
                i = 2;
            }
            House.setClipRect(g, (screenWidth - 18) >> 1, ((screenHeight - promptMarginY) - 18) - 4, 18, 18);
            g.drawImage(arrowSprite, (screenWidth - 18) >> 1, ((screenHeight - promptMarginY) - ((i + 1) * 18)) - 4, 20);
            if (promptPage > 0) {
                House.setClipRect(g, (screenWidth - 18) >> 1, promptMarginY + 4, 18, 18);
                g.drawImage(arrowSprite, (screenWidth - 18) >> 1, promptMarginY + 4, 20);
            }
        }
    }

    private static void toppleBlocks(int fromSlot, int reason) {
        int i3;
        int i4;
        if (reason != -1) {
            i3 = topFloorSlot;
            if (reason > 4) {
                reason = 4;
            }
            for (i4 = 0; i4 < reason; i4++) {
                if (blockCount == 1) {
                    House.loseLives(1);
                    return;
                }
                blockTargetAngle[i4 + 1] = (-fromSlot) * 45;
                blockVelY[i4 + 1] = ((4 - i4) * 30) + 50;
                blockVelX[i4 + 1] = (fromSlot * 400) - ((4 - i4) * 30);
                blockState[i4 + 1] = BLOCK_TUMBLING;
                blockY[i4 + 1] = floorY[i3];
                blockX[i4 + 1] = floorX[i3];
                blockStartY[i4 + 1] = blockY[i4 + 1];
                blockTargetTilt[i4 + 1] = (1 - (House.random(2) * 2)) * 60;
                blockTilt[i4 + 1] = 0;
                slotDelay[i4 + 1] = 0;
                blockAngle[i4 + 1] = floorAngle[i3];
                House.evacuateFloor(blockCount);
                House.changeFloorCount(-1);
                i3--;
            }
            if (!blockLostThisFrame) {
                House.loseLives(1);
                return;
            }
            return;
        }
        int i5 = 20;
        i3 = 1;
        for (i4 = Math.min(4, blockCount - 1); i4 > 0; i4--) {
            int i6 = floorX[i4] - floorX[i4 - 1];
            if (Math.abs(i6) <= i5) {
                break;
            }
            i6 /= Math.abs(i6);
            Math.min(5 - i3, i4);
            blockTargetAngle[i3] = (-i6) * 45;
            blockVelY[i3] = 50;
            blockVelX[i3] = i6 * 400;
            blockState[i3] = BLOCK_DEBRIS_WAITING;
            slotDelay[i3] = slotDelays[i3];
            blockTargetTilt[i3] = (1 - (House.random(2) * 2)) * 60;
            blockTilt[i3] = 0;
            i3++;
            i5 <<= 1;
        }
        House.loseLives(1);
    }

    private static int mapKey(int keyCode, int gameAction) {
        int i3 = -1;
        if (keyCode == 53) {
            i3 = 0;
        } else if (keyCode == 56) {
            i3 = 2;
        } else if (keyCode == 50) {
            i3 = 1;
        }
        return gameAction == 8 ? 0 : gameAction == 6 ? 2 : gameAction == 1 ? 1 : i3;
    }

    private static void paintFlyers(Graphics g) {
        int frameW;
        int frameH;
        int i = (camY * 3) / 4;
        for (int i2 = 0; i2 < 9; i2++) {
            int[] iArr = flyers[i2];
            int i3 = iArr[0];
            if (i3 != 0) {
                int i4 = (centerX + ((iArr[1] - camX) * 32)) >> 8;
                int i5 = (centerY - ((iArr[2] - i) * 32)) >> 8;
                if (i3 == 13) {
                    g.setColor(255, 255, 255);
                    g.drawLine(i4, i5, i4, i5);
                } else if (i3 == 6 || i3 == 12) {
                    frameW = flyerFrames[i3 - 1].getWidth() >> 1;
                    frameH = flyerFrames[i3 - 1].getHeight();
                    House.setClipRect(g, i4 - (frameW >> 1), i5 - (frameH >> 1), frameW, frameH);
                    g.drawImage(flyerFrames[i3 - 1], (i4 - (frameW >> 1)) - (frameW * ((gameTime / 400) % 2)), i5 - (frameH >> 1), 20);
                    House.setClipRect(g, 0, 0, screenWidth, screenHeight);
                } else if (i3 == 28) {
                    frameW = flyerFrames[i3 - 1].getWidth();
                    frameH = flyerFrames[i3 - 1].getHeight() >> 1;
                    House.setClipRect(g, i4 - (frameW >> 1), i5 - (frameH >> 1), frameW, frameH);
                    g.drawImage(flyerFrames[i3 - 1], i4 - (frameW >> 1), (i5 - (frameH >> 1)) - (((gameTime / 400) % 2) * frameH), 20);
                    House.setClipRect(g, 0, 0, screenWidth, screenHeight);
                } else {
                    g.drawImage(flyerFrames[i3 - 1], i4, i5, 3);
                }
            }
        }
    }

    public static boolean updateLoadingProgress(int progressPercent) {
        loadingProgress = progressPercent;
        GameMIDlet.getInstance().fullRepaint();
        return !loadCancelled;
    }

    protected static void saveTowerQuickMode() {
        try {
            int i;
            int i2;
            DataOutputStream dos = Storage.openWrite("quickModeRS");
            dos.writeInt(gameMode);
            dos.writeInt(screenState);
            dos.writeInt(phase);
            dos.writeInt(gameTime);
            dos.writeInt(centerX);
            dos.writeInt(centerY);
            dos.writeInt(camX);
            dos.writeInt(camY);
            dos.writeInt(camZ);
            dos.writeInt(camTargetY);
            dos.writeInt(camEaseStart);
            dos.writeInt(pivotX);
            dos.writeInt(pivotY);
            dos.writeInt(hookX);
            dos.writeInt(hookY);
            dos.writeInt(swingAngle);
            dos.writeInt(craneTime);
            dos.writeInt(craneYOffset);
            dos.writeInt(ropeLength);
            dos.writeInt(swingPeriod);
            dos.writeInt(prevHookX);
            dos.writeInt(prevHookY);
            dos.writeInt(swingAmplitudeX);
            dos.writeInt(swingAmplitudeY);
            dos.writeInt(skyStage);
            dos.writeInt(fenceHeight);
            dos.writeInt(fenceContainerWidth);
            dos.writeInt(fenceChainWidth);
            dos.writeInt(fenceWoodWidth);
            dos.writeInt(skyStageLength);
            dos.writeInt(blockCount);
            dos.writeInt(blockGoal);
            dos.writeInt(towerType);
            dos.writeInt(foundationOffset);
            dos.writeInt(unusedValue);
            dos.writeInt(scrolledOffsetSum);
            dos.writeInt(offsetSum);
            dos.writeInt(topFloorSlot);
            dos.writeInt(swayAngle);
            dos.writeInt(swayAmplitude);
            dos.writeInt(swayPhase);
            for (int value : floorX) {
                dos.writeInt(value);
            }
            for (int value : floorY) {
                dos.writeInt(value);
            }
            for (int value : floorSway) {
                dos.writeInt(value);
            }
            for (int value : floorAngle) {
                dos.writeInt(value);
            }
            dos.writeBoolean(bonusEligible);
            dos.writeInt(floorIndex);
            dos.writeInt(landingQuality);
            for (int value : blockX) {
                dos.writeInt(value);
            }
            for (int value : blockY) {
                dos.writeInt(value);
            }
            for (int value : blockState) {
                dos.writeInt(value);
            }
            for (int value : blockTilt) {
                dos.writeInt(value);
            }
            for (int value : blockAngle) {
                dos.writeInt(value);
            }
            for (int value : blockVelX) {
                dos.writeInt(value);
            }
            for (int value : blockVelY) {
                dos.writeInt(value);
            }
            for (int value : blockStartY) {
                dos.writeInt(value);
            }
            for (int value : blockTargetAngle) {
                dos.writeInt(value);
            }
            for (byte writeByte : landingOffsets) {
                dos.writeByte(writeByte);
            }
            for (int value : blockTargetTilt) {
                dos.writeInt(value);
            }
            for (int row = 0; row < 8; row++) {
                for (i = 0; i < 8; i++) {
                    dos.writeInt(citizens[row][i]);
                }
            }
            for (int row = 0; row < 2; row++) {
                for (i = 0; i < 5; i++) {
                    dos.writeInt(lostBlocks[row][i]);
                }
            }
            dos.writeInt(hudX);
            dos.writeInt(hudHeight);
            dos.writeInt(score);
            dos.writeInt(lives);
            dos.writeInt(lastLossAmount);
            dos.writeInt(lastLossTime);
            dos.writeInt(lastPerfectTime);
            dos.writeInt(comboTimer);
            dos.writeInt(comboBonus);
            dos.writeInt(comboCount);
            dos.writeInt(lastLandTime);
            dos.writeInt(skylineStart);
            for (int row = 0; row < 12; row++) {
                for (i = 0; i < 7; i++) {
                    dos.writeInt(clouds[row][i]);
                }
            }
            for (int row = 0; row < 9; row++) {
                for (i = 0; i < 6; i++) {
                    dos.writeInt(flyers[row][i]);
                }
            }
            for (int value : progress) {
                dos.writeInt(value);
            }
            dos.writeBoolean(roundFinishedQuick);
            dos.writeInt(maxCombo);
            dos.writeBoolean(perfectTipActiveQuick);
            dos.writeBoolean(newRecord);
            dos.writeBoolean(raining);
            dos.writeBoolean(snowing);
            dos.writeBoolean(reservedFlagA);
            dos.writeBoolean(rainTriggered);
            dos.writeBoolean(snowTriggered);
            dos.writeBoolean(lightingEnabled);
            dos.writeBoolean(reservedFlagB);
            dos.writeInt(lightLevel);
            dos.writeInt(lightBefore);
            dos.writeInt(lightTarget);
            dos.writeInt(lightFadeDuration);
            dos.writeInt(flashLevel);
            dos.writeInt(flashPeak);
            dos.writeInt(flashStart);
            dos.writeInt(flashTimer);
            dos.writeInt(flashDuration);
            dos.writeInt(reservedStateFlag);
            dos.writeInt(weatherPhase);
            dos.writeInt(phaseDuration);
            dos.writeInt(rampUpDuration);
            dos.writeInt(peakDuration);
            dos.writeInt(rampDownDuration);
            dos.writeInt(weatherTimer);
            dos.writeInt(weatherType);
            dos.writeInt(reservedValue);
            for (int row = 0; row < 2; row++) {
                for (i = 0; i < 7; i++) {
                    dos.writeInt(weatherEmitters[row][i]);
                }
            }
            dos.writeInt(lastHitTime);
            Storage.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in saveTowerQuickMode(), e:").append(e.getMessage()).toString());
        }
    }

    private void render3D(Graphics g) {
        int i;
        Mesh3D dVar = null;
        Renderer3D.setFov(55.0f);
        Renderer3D.lookAt((float) camX, (float) camY, (float) camZ, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
        if (phase == PHASE_ROOF || blockState[0] == BLOCK_RETURNING || (phase == PHASE_INTRO && blockState[0] != BLOCK_DROPPED)) {
            Renderer3D.projected[0] = (float) hookX;
            Renderer3D.projected[1] = (float) (hookY + 528);
            Renderer3D.projected[2] = 0.0f;
            Renderer3D.projected[3] = 1.0f;
            Renderer3D.project(Renderer3D.projected);
            g.setColor(0);
            i = centerX + (((pivotX - camX) * 32) >> 8);
            int i2 = centerY - (((pivotY - camY) * 32) >> 8);
            int i3 = (int) Renderer3D.projected[0];
            int i4 = (int) Renderer3D.projected[1];
            g.drawImage(craneArmSprite, i + 4, i2, 40);
            g.drawLine(i, i2, i3, i4);
            g.drawLine(i + 1, i2, i3 + 1, i4);
        }
        Renderer3D.beginFrame((Object) g);
        if (blockCount <= 5) {
            groundMesh.resetTransform();
            groundMesh.render();
        }
        if (phase == PHASE_PLAYING) {
            craneHookMesh.resetTransform();
            craneHookMesh.translate((float) hookX, (float) hookY, 0.0f);
            craneHookMesh.rotate(((float) swingAngle) / 540.0f, 0.0f, 0.0f, 1.0f);
            dVar = craneHookMesh;
        } else if (phase == PHASE_ROOF || blockState[0] == BLOCK_RETURNING || (phase == PHASE_INTRO && blockState[0] != BLOCK_DROPPED)) {
            craneHookStaticMesh.resetTransform();
            craneHookStaticMesh.translate((float) hookX, (float) hookY, 0.0f);
            dVar = craneHookStaticMesh;
        }
        if (dVar != null) {
            dVar.render();
        }
        i = 0;
        while (i < 5) {
            if (!(blockState[i] == BLOCK_LANDED || blockState[i] == 0)) {
                House.renderBlock(blockX[i], blockY[i], 0, blockAngle[i], blockTilt[i]);
            }
            i++;
        }
        for (i = 0; i < Math.min(blockCount, 5); i++) {
            House.renderBlock(floorX[i], floorY[i], floorSway[i], -floorAngle[i], 0);
        }
        Renderer3D.endFrame();
    }

    private static void paintLoadingScreen(Graphics g) {
        House.paintSky(g, (loadingScroll * 32) >> 8, true, false);
        int i = (screenWidth << 1) / 3;
        int height = (screenHeight >> 1) + (loadingImage.getHeight() >> 3);
        g.drawImage(loadingImage, screenWidth >> 1, height, 33);
        height += loadingImage.getHeight() >> 3;
        g.setColor(-15463412);
        g.fillRect(((screenWidth >> 1) - (i >> 1)) + 1, height + 1, i, 10);
        g.setColor(-8556946);
        g.fillRect((screenWidth >> 1) - (i >> 1), height, i, 10);
        g.setColor(-1);
        g.fillRect(((screenWidth >> 1) - (i >> 1)) + 1, height + 1, i - 2, 8);
        g.setColor(-14475756);
        g.fillRect(((screenWidth >> 1) - (i >> 1)) + 2, height + 2, i - 4, 6);
        g.setColor(-4980736);
        g.fillRect(((screenWidth >> 1) - (i >> 1)) + 2, height + 2, (loadingProgress * (i - 4)) / 100, 6);
        g.setColor(-9895936);
        g.fillRect(((screenWidth >> 1) - (i >> 1)) + 3, height + 3, ((loadingProgress * (i - 4)) / 100) - 1, 5);
        g.setColor(-487168);
        g.fillRect(((screenWidth >> 1) - (i >> 1)) + 3, height + 3, ((loadingProgress * (i - 4)) / 100) - 2, 1);
        g.setColor(-510464);
        g.fillRect(((screenWidth >> 1) - (i >> 1)) + 3, height + 4, (((i - 4) * loadingProgress) / 100) - 2, 3);
    }

    protected static void saveTowerCityMode() {
        try {
            int i;
            int i2;
            DataOutputStream dos = Storage.openWrite("cityModeRS");
            dos.writeInt(gameMode);
            dos.writeInt(screenState);
            dos.writeInt(phase);
            dos.writeInt(gameTime);
            dos.writeInt(centerX);
            dos.writeInt(centerY);
            dos.writeInt(camX);
            dos.writeInt(camY);
            dos.writeInt(camZ);
            dos.writeInt(camTargetY);
            dos.writeInt(camEaseStart);
            dos.writeInt(pivotX);
            dos.writeInt(pivotY);
            dos.writeInt(hookX);
            dos.writeInt(hookY);
            dos.writeInt(swingAngle);
            dos.writeInt(craneTime);
            dos.writeInt(craneYOffset);
            dos.writeInt(ropeLength);
            dos.writeInt(swingPeriod);
            dos.writeInt(prevHookX);
            dos.writeInt(prevHookY);
            dos.writeInt(swingAmplitudeX);
            dos.writeInt(swingAmplitudeY);
            dos.writeInt(skyStage);
            dos.writeInt(fenceHeight);
            dos.writeInt(fenceContainerWidth);
            dos.writeInt(fenceChainWidth);
            dos.writeInt(fenceWoodWidth);
            dos.writeInt(skyStageLength);
            dos.writeInt(blockCount);
            dos.writeInt(blockGoal);
            dos.writeInt(towerType);
            dos.writeInt(foundationOffset);
            dos.writeInt(unusedValue);
            dos.writeInt(scrolledOffsetSum);
            dos.writeInt(offsetSum);
            dos.writeInt(topFloorSlot);
            dos.writeInt(swayAngle);
            dos.writeInt(swayAmplitude);
            dos.writeInt(swayPhase);
            for (int value : floorX) {
                dos.writeInt(value);
            }
            for (int value : floorY) {
                dos.writeInt(value);
            }
            for (int value : floorSway) {
                dos.writeInt(value);
            }
            for (int value : floorAngle) {
                dos.writeInt(value);
            }
            dos.writeBoolean(bonusEligible);
            dos.writeInt(floorIndex);
            dos.writeInt(landingQuality);
            for (int value : blockX) {
                dos.writeInt(value);
            }
            for (int value : blockY) {
                dos.writeInt(value);
            }
            for (int value : blockState) {
                dos.writeInt(value);
            }
            for (int value : blockTilt) {
                dos.writeInt(value);
            }
            for (int value : blockAngle) {
                dos.writeInt(value);
            }
            for (int value : blockVelX) {
                dos.writeInt(value);
            }
            for (int value : blockVelY) {
                dos.writeInt(value);
            }
            for (int value : blockStartY) {
                dos.writeInt(value);
            }
            for (int value : blockTargetAngle) {
                dos.writeInt(value);
            }
            for (byte writeByte : landingOffsets) {
                dos.writeByte(writeByte);
            }
            for (int value : blockTargetTilt) {
                dos.writeInt(value);
            }
            for (int row = 0; row < 8; row++) {
                for (i = 0; i < 8; i++) {
                    dos.writeInt(citizens[row][i]);
                }
            }
            for (int row = 0; row < 2; row++) {
                for (i = 0; i < 5; i++) {
                    dos.writeInt(lostBlocks[row][i]);
                }
            }
            dos.writeInt(hudX);
            dos.writeInt(hudHeight);
            dos.writeInt(score);
            dos.writeInt(lives);
            dos.writeInt(lastLossAmount);
            dos.writeInt(lastLossTime);
            dos.writeInt(lastPerfectTime);
            dos.writeInt(comboTimer);
            dos.writeInt(comboBonus);
            dos.writeInt(comboCount);
            dos.writeInt(lastLandTime);
            dos.writeInt(skylineStart);
            for (int row = 0; row < 12; row++) {
                for (i = 0; i < 7; i++) {
                    dos.writeInt(clouds[row][i]);
                }
            }
            for (int row = 0; row < 9; row++) {
                for (i = 0; i < 6; i++) {
                    dos.writeInt(flyers[row][i]);
                }
            }
            for (int value : progress) {
                dos.writeInt(value);
            }
            dos.writeBoolean(roundFinishedCity);
            dos.writeInt(maxCombo);
            dos.writeBoolean(House.perfectTipActiveCity);
            dos.writeBoolean(newRecord);
            dos.writeBoolean(raining);
            dos.writeBoolean(snowing);
            dos.writeBoolean(reservedFlagA);
            dos.writeBoolean(rainTriggered);
            dos.writeBoolean(snowTriggered);
            dos.writeBoolean(lightingEnabled);
            dos.writeBoolean(reservedFlagB);
            dos.writeInt(lightLevel);
            dos.writeInt(lightBefore);
            dos.writeInt(lightTarget);
            dos.writeInt(lightFadeDuration);
            dos.writeInt(flashLevel);
            dos.writeInt(flashPeak);
            dos.writeInt(flashStart);
            dos.writeInt(flashTimer);
            dos.writeInt(flashDuration);
            dos.writeInt(reservedStateFlag);
            dos.writeInt(weatherPhase);
            dos.writeInt(phaseDuration);
            dos.writeInt(rampUpDuration);
            dos.writeInt(peakDuration);
            dos.writeInt(rampDownDuration);
            dos.writeInt(weatherTimer);
            dos.writeInt(weatherType);
            dos.writeInt(reservedValue);
            for (int row = 0; row < 2; row++) {
                for (i = 0; i < 7; i++) {
                    dos.writeInt(weatherEmitters[row][i]);
                }
            }
            dos.writeInt(lastHitTime);
            Storage.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in saveTowerCityMode(), e:").append(e.getMessage()).toString());
        }
    }

    private static void paintPerfectEffect(Graphics g) {
        int i = 0;
        int i2 = gameTime - lastPerfectTime;
        if (i2 < 600) {
            int i3 = 48;
            int i4 = 32;
            int i5 = centerX + (((floorX[topFloorSlot] - camX) * 32) / 256);
            int i6 = centerY - (((floorY[topFloorSlot] - camY) * 32) / 256);
            if (i2 < 30) {
                g.setColor(16777215);
            } else if (i2 < 200) {
                g.setColor(255, 255 - (i2 >> 2), 0);
                i4 = 48 - ((i2 * 32) / 200);
                i3 = Math.max(2, 6 - ((i2 * 6) / 200));
                g.fillRect(i5 - (i4 >> 1), ((32 - i3) >> 1) + i6, i4, i3);
                i3 = ((i2 * 32) / 200) + 16;
                i4 = ((i2 * 32) / 400) + 16;
                g.drawLine(i5 + 16, i6 + 16, i5 + i3, i6 + i4);
                g.drawLine(i5 - 16, i6 + 16, i5 - i3, i6 + i4);
                g.drawLine(i5 - 16, i6 - 16, i5 - i4, i6 - i4);
            } else {
                i = (i2 / 50) % 3;
            }
            House.setClipRect(g, (i5 + i3) - 6, (i6 + i4) - 6, 13, 13);
            g.drawImage(sparkSprite, ((i5 + i3) - 6) - (i * 13), (i6 - 6) + i4, 20);
            House.setClipRect(g, (i5 - i3) - 6, (i6 + i4) - 6, 13, 13);
            g.drawImage(sparkSprite, ((i5 - i3) - 6) - (i * 13), i4 + (i6 - 6), 20);
            House.setClipRect(g, (i5 - i3) - 6, (i6 - i3) - 6, 13, 13);
            g.drawImage(sparkSprite, ((i5 - i3) - 6) - (i * 13), (i6 - 6) - i3, 20);
        }
    }

    public static int random(int bound) {
        return Math.abs(rng.nextInt() % bound);
    }

    private static void paintScenery(Graphics g) {
        int i;
        Renderer3D.lookAt((float) camX, (float) camY, (float) camZ, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
        Renderer3D.projected[0] = 0.0f;
        Renderer3D.projected[1] = -19.0f;
        Renderer3D.projected[2] = -80.0f;
        Renderer3D.projected[3] = 1.0f;
        Renderer3D.project(Renderer3D.projected);
        int i2 = (((int) Renderer3D.projected[1]) - fenceHeight) + 9;
        House.paintSky(g, camY, false, true);
        House.paintClouds(g);
        House.paintSkyline(g, i2);
        House.paintFlyers(g);
        if (i2 - treeWidth < screenHeight) {
            g.drawImage(treeSprite, (screenWidth >> 1) + (fenceContainerWidth >> 1), (i2 - treeWidth) + 9, 17);
        }
        if (i2 < screenHeight) {
            int i3 = (screenWidth >> 1) - (fenceContainerWidth >> 1);
            Image image = fenceContainerSprite;
            int i4 = i3;
            Graphics graphics2 = g;
            while (true) {
                graphics2.drawImage(image, i4, i2, 20);
                i3 = i4 - fenceChainWidth;
                if (i3 <= 0 - fenceChainWidth) {
                    break;
                }
                image = fenceChainSprite;
                i4 = i3;
                graphics2 = g;
            }
            i3 = screenWidth >> 1;
            i = fenceContainerWidth >> 1;
            while (true) {
                i3 += i;
                if (i3 >= screenWidth) {
                    break;
                }
                g.drawImage(fenceWoodSprite, i3, i2, 20);
                i = fenceWoodWidth;
            }
        }
        i = fenceHeight + i2;
        if (i < screenHeight) {
            g.setColor(4602900);
            g.fillRect(0, i, screenWidth, 2);
            g.setColor(1972495);
            g.fillRect(0, i + 2, screenWidth, (screenHeight - 2) - i);
        }
    }

    public static int sin(int angleBrads) {
        return angleBrads < 0 ? -sinTable[Math.abs(angleBrads)] : sinTable[angleBrads];
    }

    private static void paintHud(Graphics g) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        House.setClipRect(g, ((hudX + (hudX >> 1)) + 14) + 4, (screenHeight - hudHeight) - 72, 11, 72);
        i4 = 0;
        while (i4 <= 3) {
            i5 = (towerType - 1) * 2;
            if (i4 > lives) {
                i5 = (gameTime - lastLossTime >= 100 || i4 > lives + lastLossAmount) ? 8 : ((towerType - 1) * 2) + 1;
            } else if (lives == 1 && (gameTime / 500) % 2 == 0) {
                i5 = 9;
            }
            g.drawImage(lifeIcons, (((hudX + (hudX >> 1)) + 14) - (i5 * 11)) + 4, (screenHeight - hudHeight) - (i4 * 12), 20);
            i4++;
        }
        if (blockCount > 0) {
            House.drawDigitsA(g, score, screenWidth - hudX, ((screenHeight - hudHeight) - 2) - 12, 5, false);
            House.setClipRect(g, (((screenWidth - hudX) - 28) - 3) - 11, ((screenHeight - hudHeight) - 2) - 19, 11, 19);
            g.drawImage(hudIcons, (((screenWidth - hudX) - 28) - 3) - 11, ((screenHeight - hudHeight) - 2) - 19, 20);
        }
        if (gameMode == GAME_MODE_CITY_TOWER) {
            House.setClipRect(g, hudX - 3, ((screenHeight - hudHeight) - 21) - (blockGoal * 3), 20, 21);
            i5 = towerType - 1;
            if (phase == PHASE_ROOF && (gameTime / 500) % 2 == 0) {
                i5 = 4;
            }
            g.drawImage(levelMedals, (hudX - 3) - (i5 * 20), ((screenHeight - hudHeight) - 21) - (blockGoal * 3), 20);
            for (i5 = 0; i5 < Math.min(blockCount, blockGoal - 1); i5++) {
                House.setClipRect(g, hudX, ((screenHeight - hudHeight) - 2) - ((i5 + 1) * 3), 14, 3);
                g.drawImage(hudIcons, hudX + 2, ((((screenHeight - hudHeight) - 2) - 20) - ((i5 + 1) * 3)) - ((towerType - 1) * 3), 20);
            }
            House.setClipRect(g, hudX - 1, (screenHeight - hudHeight) - 2, 14, 2);
            g.drawImage(hudIcons, (hudX + 2) - 1, (((screenHeight - hudHeight) - 2) - 28) - 1, 20);
            House.setClipRect(g, 0, 0, screenWidth, screenHeight);
            g.setColor(4725760);
            g.drawLine(hudX - 1, ((screenHeight - hudHeight) - 2) - ((blockGoal - 1) * 3), hudX - 1, (screenHeight - hudHeight) - 2);
            g.drawLine(hudX + 14, ((screenHeight - hudHeight) - 2) - ((blockGoal - 1) * 3), hudX + 14, (screenHeight - hudHeight) - 2);
            g.setColor(16776960);
            g.drawLine(hudX, ((screenHeight - hudHeight) - 2) - ((blockGoal - 1) * 3), hudX, (screenHeight - hudHeight) - 2);
            g.drawLine((hudX + 14) - 1, ((screenHeight - hudHeight) - 2) - ((blockGoal - 1) * 3), (hudX + 14) - 1, (screenHeight - hudHeight) - 2);
            g.setColor(2301460);
            g.fillRect(hudX + 1, ((screenHeight - hudHeight) - 2) - ((blockGoal - 1) * 3), 12, ((blockGoal - blockCount) - 1) * 3);
            if (gameTime - lastLandTime < 100 && phase != PHASE_ROUND_OVER) {
                g.setColor(16776960);
                g.fillRect(hudX + 1, ((screenHeight - hudHeight) - 2) - (blockCount * 3), 12, 3);
            }
        } else {
            House.setClipRect(g, 0, 0, screenWidth, screenHeight);
            g.drawImage(floorCounterFrame, hudX - 9, (screenHeight - hudHeight) - 50, 20);
            House.drawDigitsB(g, blockCount, (hudX - 9) + 23, ((screenHeight - hudHeight) - 50) + 37, 3, false);
        }
        House.setClipRect(g, 0, 0, screenWidth, screenHeight);
        if (comboTimer > 0) {
            g.setColor(107, 26, 0);
            g.drawRect((((screenWidth >> 1) - (screenWidth >> 2)) - 2) - 1, hudHeight - 1, ((screenWidth >> 1) + 4) + 1, 9);
            g.setColor(252, 255, 0);
            g.drawRect(((screenWidth >> 1) - (screenWidth >> 2)) - 2, hudHeight, ((screenWidth >> 1) + 4) - 1, 7);
            if (comboTimer > 5850) {
                g.setColor(255, 255, 255);
            }
            g.fillRect((screenWidth >> 1) - (screenWidth >> 2), hudHeight + 2, (comboTimer * (screenWidth >> 1)) / 6000, 4);
            House.setClipRect(g, ((screenWidth >> 1) - (screenWidth >> 2)) - 11, hudHeight - 7, 22, 22);
            g.drawImage(comboStarSprite, (((screenWidth >> 1) - (screenWidth >> 2)) - 11) - (((gameTime / 80) % 4) * 22), hudHeight - 7, 20);
            if (blockState[0] == BLOCK_HANGING || blockState[0] == BLOCK_DROPPED) {
                i5 = centerX + (((blockX[0] - camX) * 32) >> 8);
                i4 = centerY - (((blockY[0] - camY) * 32) >> 8);
                House.setClipRect(g, i5 - 22, i4 - 22, 44, 44);
                g.drawImage(comboSparkleSprite, (i5 - 22) - (((gameTime / 100) % 3) * 44), i4 - 22, 20);
            }
            if (comboCount > 0) {
                for (i5 = topFloorSlot; i5 > Math.max(0, topFloorSlot - comboCount); i5--) {
                    i4 = centerX + (((floorX[i5] - camX) * 32) >> 8);
                    i = centerY - (((floorY[i5] - camY) * 32) >> 8);
                    House.setClipRect(g, i4 - 22, i - 22, 44, 44);
                    g.drawImage(comboSparkleSprite, (i4 - 22) - ((((gameTime / 100) + i5) % 3) * 44), i - 22, 20);
                }
                if (comboCount > 1) {
                    House.setClipRect(g, ((screenWidth >> 1) + (screenWidth >> 2)) + 5, hudHeight, 7, 12);
                    g.drawImage(digitSheetA, (((screenWidth >> 1) + (screenWidth >> 2)) + 5) - 91, hudHeight - 2, 20);
                    i5 = comboCount > 9 ? 2 : 1;
                    House.drawDigitsA(g, comboCount, ((((screenWidth >> 1) + (screenWidth >> 2)) + 10) + 2) + (i5 * 7), hudHeight - 2, i5, false);
                }
            }
            return;
        } else if (comboBonus != 0 && comboTimer > -2000 && ((-comboTimer) / 100) % 2 == 0) {
            House.drawDigitsA(g, comboBonus, (screenWidth >> 1) + 14, hudHeight, 3, true);
        }
    }

    public static int negCos(int angleBrads) {
        return House.sin(angleBrads - 90);
    }

    private static void paintCitizens(Graphics g) {
        for (int i = 0; i < 8; i++) {
            if (citizens[0][i] != 0) {
                House.drawCitizen(g, (centerX + (((citizens[7][i] - camX) * 32) >> 8)) - 10, (centerY - (((citizens[8][i] - camY) * 32) >> 8)) - 14, citizens[3][i], citizens[6][i], citizens[10][i]);
            }
        }
    }

    private static void paintClouds(Graphics g) {
        int i = (camY * 32) >> 8;
        for (int i2 = 0; i2 < 12; i2++) {
            int[] iArr = clouds[i2];
            if (iArr[4] != 0) {
                int i3;
                int i4 = clouds[i2][2];
                int i5 = (centerY - i4) + i;
                int i6 = i5 + i4;
                if (i5 < 0) {
                    i5 = 0;
                    i3 = 0;
                } else {
                    i3 = 1;
                }
                if (i5 + i6 > screenHeight) {
                    i6 = screenHeight - i5;
                }
                if (i5 > screenHeight + 32) {
                    clouds[i2][4] = 0;
                } else {
                    g.setColor(House.shadeColor(iArr[5], lightLevel, true));
                    int i7 = iArr[1];
                    if (i5 < screenHeight) {
                        g.fillRect(clouds[i2][0], i5, i7, i6);
                    }
                    if (i3 != 0) {
                        int roofType = iArr[6];
                        if (roofType == 1) {
                            g.fillRect(clouds[i2][0] + (i7 >> 1), i5 - clouds[i2][3], 3, clouds[i2][3]);
                            if (((gameTime + i4) / 200) % 2 == 0) {
                                g.setColor(16711680);
                                g.fillRect(clouds[i2][0] + (i7 >> 1), i5 - clouds[i2][3], 3, 3);
                            }
                        } else if (roofType == 2) {
                            g.fillRect(clouds[i2][0] + (i7 / 10), i5 - 16, i7 - (i7 / 5), 16);
                        }
                    }
                }
            }
        }
    }

    public static boolean handleModalAction(int action) {
        if (gameTime >= promptShownTime && gameTime - promptShownTime < 300) {
            return false;
        }
        House.clearKeys();
        boolean O = House.isLastPromptPage();
        if (!O || promptOptions == null) {
            if (action == 0) {
                action = 1;
            }
            promptPage += action;
            if (promptPage < 0) {
                promptPage = 0;
            } else if (promptPage >= promptPageCount && O) {
                House.resetPrompt();
                return true;
            }
            return false;
        }
        promptSelection = Math.min(promptSelection + action, promptOptions.length - 1);
        if (promptSelection < 0) {
            promptPage = Math.max(0, promptPage - 1);
            promptSelection = 0;
        } else if (action == 0) {
            House.resetPrompt();
            return true;
        }
        return false;
    }

    private static void raiseCamera(int targetHeight) {
        camEaseStart = gameTime;
        camTargetY = blockCount > 1 ? camTargetY + targetHeight : 512;
    }

    public static Font getGameFont() {
        return gameFont;
    }

    private static void updateCrane(int dt) {
        craneTime += dt;
        if (blockState[0] == BLOCK_ROPE_LOWERING) {
            ropeLength += (dt * 2) / 3;
            int i2 = 1664;
            if (phase == PHASE_ROOF) {
                i2 = 1408;
            }
            if (ropeLength >= i2) {
                ropeLength = i2;
                blockState[0] = BLOCK_HANGING;
            }
        }
        hookX = (swingAmplitudeX * House.negCos(((craneTime * 200) / swingPeriod) % 360)) >> 15;
        swingAngle = hookX >> 4;
        hookY = (-((swingAmplitudeY * House.sin(((craneTime * 200) / swingPeriod) % 360)) >> 15)) + ((pivotY - craneYOffset) - ropeLength);
        if (blockState[0] == BLOCK_HANGING && phase != PHASE_INTRO) {
            blockVelX[0] = ((hookX - prevHookX) * 256) / dt;
            blockVelY[0] = ((hookY - prevHookY) * 256) / dt;
        }
        prevHookX = hookX;
        prevHookY = hookY;
    }

    private static void updateTowerSway(int dt) {
        int i2;
        swayCos = 0;
        if (phase != PHASE_ROUND_OVER) {
            swayPhase = (swayPhase + dt) % 3600;
            swayCos = House.negCos(swayPhase / 10);
            i2 = (swayAmplitude * swayCos) >> 16;
        } else if (swayAngle > 0) {
            i2 = swayAngle - 1;
        } else if (swayAngle < 0) {
            i2 = swayAngle + 1;
        } else {
            i2 = 0;
        }
        swayAngle = i2;
        swayOffset = (-(swayCos * swayAmplitude)) / 10000;
        House.negCos(swayAngle);
    }

    private static void nudgeSwayPhase(int deltaPhase) {
        Math.max(Math.abs(0), 80);
        int i2 = 1;
        if (blockVelX[0] != 0) {
            i2 = blockVelX[0] / Math.abs(blockVelX[0]);
        }
        if (swayPhase < 1800) {
            i2 = swayPhase - ((i2 * 0) * 2);
        } else {
            i2 = ((i2 * 0) * 2) + swayPhase;
        }
        swayPhase = i2;
    }

    // ---- Block slots -------------------------------------------------------------------------
    // blockState/blockX/blockY/... are parallel arrays indexed by slot. Slot 0 is the block on the
    // crane (the one the player drops); slots 1..4 are chunks of tower that break off when a drop
    // misses or topples the tower ("debris").
    private static final int BLOCK_SLOTS = 5;
    private static final int CRANE_SLOT = 0;

    private static final int BLOCK_HANGING = 1;         // on the hook, ready to be dropped
    private static final int BLOCK_DROPPED = 2;         // player's block in free fall, being checked for landing
    private static final int BLOCK_RETURNING = 3;       // missed the roof: hoisted back up to the crane
    private static final int BLOCK_LANDED = 4;          // settled (or fell out of view); waiting to be re-hung
    private static final int BLOCK_TUMBLING = 5;        // falling and tumbling (missed block or debris)
    private static final int BLOCK_ROPE_LOWERING = 6;   // new block being paid out on the rope
    private static final int BLOCK_DEBRIS_WAITING = 7;  // debris waiting for its slotDelay before falling

    // Round phase (House.phase).
    private static final int PHASE_PLAYING = 0;
    private static final int PHASE_ROOF = 1;        // last floor placed, waiting for the roof
    private static final int PHASE_ROUND_OVER = 2;  // out of lives or roof placed; result screen after 2 s
    private static final int PHASE_RESULT = 3;      // result screen shown
    private static final int PHASE_INTRO = 4;       // first-time tutorial, before the first drop (inferred)

    // House.gameMode: 5 = city-tower mode (finished towers are placed in the city; has a goal height and a roof),
    // 6 = quick mode (endless tower). Confirmed by the save/load pairing (5 -> saveTowerCityMode, 6 -> saveTowerQuickMode).
    private static final int GAME_MODE_CITY_TOWER = 5;
    private static final int GAME_MODE_QUICK = 6;

    // Sentinels stored in blockAngle/blockTilt.
    private static final int ANGLE_NONE = 999;      // no rotation animation
    private static final int ANGLE_RETURNING = 888; // block is travelling back up to the crane

    // Indices into lostBlocks[][].
    private static final int LOST_SCREEN_X = 0;
    private static final int LOST_TIME = 1;

    // Physics (all positions are fixed point, 256 = 1 world unit; times are milliseconds).
    private static final int ROTATION_EASE_MS = 500;      // tilt/spin ease toward target over this time
    private static final int GRAVITY_DIVISOR = 200;       // y = y0 + vy*t/256 - t*t/200
    private static final int GRAVITY_DIVISOR_ROOF = 400;  // lighter gravity while the roof block falls
    private static final int MAX_FALL_PER_FRAME = 256;    // terminal velocity
    private static final int RETURN_SPEED = 15;           // BLOCK_RETURNING climb: elapsed*15/256 per frame
    private static final int RETURN_STOP_BELOW_TOP = 512; // stops this far below pivotY
    private static final int REHANG_DELAY_MS = 400;       // delay after a landing before the next block is hung
    private static final int FULL_ROPE_LENGTH = 1664;

    /** Moves tilt/spin from {@code current} toward {@code target}, scaled by elapsed time; never overshoots. */
    private static int easeAngle(int current, int target, int elapsedMs) {
        if (current == ANGLE_NONE) {
            return current;
        }
        if (current < target) {
            return Math.min(target, current + (((target - current) * elapsedMs) / ROTATION_EASE_MS));
        }
        if (current > target) {
            return Math.max(target, current - ((elapsedMs * (current - target)) / ROTATION_EASE_MS));
        }
        return current;
    }

    /**
     * Per-frame physics for every block slot.
     *
     * @param dt milliseconds since the previous frame
     *
     * Quirk kept from the original: while slot 0 is hanging, rope-lowering or returning, the method
     * returns immediately, so debris slots 1..4 are not advanced in that frame.
     */
    private static void updateBlockPhysics(int dt) {
        blockLostThisFrame = false;
        boolean anyBlockMoving = false;

        for (int slot = 0; slot < BLOCK_SLOTS; slot++) {
            int state = blockState[slot];

            if (state == BLOCK_HANGING || state == BLOCK_ROPE_LOWERING) {
                // Block follows the hook.
                blockAngle[slot] = swingAngle;
                blockX[slot] = hookX;
                blockY[slot] = hookY;
                return;
            }

            if (state == BLOCK_RETURNING) {
                blockY[slot] += ((gameTime - fallStartTime) * RETURN_SPEED) >> 8;
                if (blockY[slot] >= pivotY - RETURN_STOP_BELOW_TOP && phase != PHASE_ROUND_OVER) {
                    // Back at the crane: pay out the rope for the next block.
                    blockState[CRANE_SLOT] = BLOCK_ROPE_LOWERING;
                    ropeLength = 0;
                    floorIndex = Math.min(4, blockCount - 1);
                    swingPeriod = swingPeriodTable[1];
                }
                hookX = blockX[CRANE_SLOT];
                hookY = blockY[CRANE_SLOT];
                blockAngle[CRANE_SLOT] = ANGLE_RETURNING;
                return;
            }

            if (state == BLOCK_DEBRIS_WAITING) {
                if (fallStartTime + slotDelay[slot] < gameTime) {
                    // Delay over: break this chunk off the top floor and let it fall.
                    blockY[slot] = floorY[topFloorSlot];
                    blockX[slot] = floorX[topFloorSlot];
                    blockState[slot] = BLOCK_TUMBLING;
                    blockStartY[slot] = blockY[slot];
                    blockAngle[slot] = floorAngle[topFloorSlot];
                    House.evacuateFloor(blockCount);
                    House.changeFloorCount(-1);
                }
                anyBlockMoving = true;
            } else if (state == BLOCK_DROPPED || state == BLOCK_TUMBLING) {
                int elapsed = (gameTime - fallStartTime) - slotDelay[slot];

                blockAngle[slot] = easeAngle(blockAngle[slot], blockTargetAngle[slot], elapsed);
                blockTilt[slot] = easeAngle(blockTilt[slot], blockTargetTilt[slot], elapsed);

                // Ballistic fall: launch velocity plus gravity, capped at terminal velocity.
                int gravityDivisor = (phase == PHASE_ROOF) ? GRAVITY_DIVISOR_ROOF : GRAVITY_DIVISOR;
                int previousY = blockY[slot];
                blockY[slot] = (blockStartY[slot] + ((blockVelY[slot] * elapsed) / 256))
                        - ((elapsed * elapsed) / gravityDivisor);
                if (blockY[slot] < previousY - MAX_FALL_PER_FRAME) {
                    blockY[slot] = previousY - MAX_FALL_PER_FRAME;
                }
                blockX[slot] = blockX[slot] + ((blockVelX[slot] * dt) / 512);

                // Fell below the bottom edge of the view.
                if (blockY[slot] < camY - (viewHeightUnits >> 1)) {
                    lostBlocks[LOST_SCREEN_X][slot] = centerX + (((blockX[slot] - camX) * 32) >> 8);
                    lostBlocks[LOST_TIME][slot] = gameTime;
                    blockState[slot] = BLOCK_LANDED;
                    if (state == BLOCK_DROPPED) {
                        // The player's block missed everything: cash in any combo and lose a life.
                        if (comboTimer > 0) {
                            House.cashInCombo();
                        }
                        House.loseLives(1);
                        blockLostThisFrame = true;
                    }
                }

                // Player's block: test for landing on the tower.
                if (state == BLOCK_DROPPED && House.handleLanding()) {
                    if (phase == PHASE_INTRO) {
                        phase = PHASE_PLAYING;
                    } else if (blockCount == blockGoal - 1 && gameMode == GAME_MODE_CITY_TOWER && phase != PHASE_ROOF) {
                        // Second-to-last floor placed in quick mode: switch to the roof phase.
                        phase = PHASE_ROOF;
                        if (comboTimer > 0) {
                            House.cashInCombo();
                        }
                        if (bonusEligible && score < scoreThresholds[towerType - 1]) {
                            bonusEligible = false;
                        }
                        blockY[CRANE_SLOT] = pivotY;
                        blockState[CRANE_SLOT] = BLOCK_ROPE_LOWERING;
                        ropeLength = 0;
                        floorIndex = Math.min(4, blockCount - 1);
                        swingPeriod = swingPeriodTable[1];
                    } else if (phase == PHASE_ROOF) {
                        // Roof placed.
                        phase = PHASE_ROUND_OVER;
                        fallStartTime = gameTime;
                    }
                }
                anyBlockMoving = true;
            }
        }

        if (phase == PHASE_ROOF || phase == PHASE_ROUND_OVER) {
            hookX = blockX[CRANE_SLOT];
            hookY = blockY[CRANE_SLOT];
            if (phase != PHASE_ROUND_OVER) {
                blockAngle[CRANE_SLOT] = ANGLE_RETURNING;
            }
        }

        // Landed and nothing else in motion: after a short pause, hang the next block.
        if (blockState[CRANE_SLOT] == BLOCK_LANDED && fallStartTime < gameTime - REHANG_DELAY_MS && !anyBlockMoving) {
            ropeLength = FULL_ROPE_LENGTH;
            if (phase != PHASE_ROUND_OVER) {
                blockState[CRANE_SLOT] = BLOCK_HANGING;
                blockX[CRANE_SLOT] = hookX;
                blockY[CRANE_SLOT] = hookY;
                blockTargetTilt[CRANE_SLOT] = 0;
                blockTilt[CRANE_SLOT] = 0;
                floorIndex = Math.max(0, Math.min(4, blockCount - 1));
            }
        }
    }


    private static void evacuateFloor(int floorSlot) {
        int abs = Math.abs(landingOffsets[floorSlot % 20]);
        abs = abs < 25 ? 4 : abs < 50 ? 3 : abs < 80 ? 2 : 1;
        int i2 = abs >> 1;
        int i3 = 0;
        while (i3 < 8) {
            if (citizens[5][i3] != floorSlot || citizens[0][i3] == 0) {
                abs = i2;
            } else {
                citizens[1][i3] = citizens[7][i3];
                citizens[2][i3] = citizens[8][i3];
                citizens[0][i3] = 3;
                citizens[5][i3] = ((citizens[1][i3] < 0 ? -1 : 1) * (citizens[4][i3] - gameTime)) / 500;
                citizens[4][i3] = gameTime;
                abs = i2 - 1;
            }
            i3++;
            i2 = abs;
        }
        i3 = 0;
        while (i3 < 8 && i2 > 0) {
            if (citizens[0][i3] == 0) {
                citizens[4][i3] = gameTime;
                citizens[0][i3] = 4;
                citizens[5][i3] = House.random(4);
                abs = House.random(2);
                if (abs == 0) {
                    abs = -1;
                }
                citizens[6][i3] = abs;
                abs = Math.min(4, blockCount - 1) - (blockCount - floorSlot);
                citizens[1][i3] = floorX[abs];
                citizens[2][i3] = floorY[abs];
                abs = i2 - 1;
            } else {
                abs = i2;
            }
            i3++;
            i2 = abs;
        }
    }

    private static void updateWeather(int dt) {
        int i2 = 2;
        weatherTimer = Math.min(weatherTimer + dt, phaseDuration);
        switch (weatherPhase) {
            case 0:
                boolean z;
                if (raining && !rainTriggered && camY >= 0 && camY <= 3840) {
                    rainTriggered = true;
                    House.clearWeather();
                    House.startWeather(1);
                    i2 = 1;
                } else if (!snowing || snowTriggered || camY < 10240 || camY > 12800) {
                    z = false;
                    if (z) {
                        lightLevel = 1024;
                        flashLevel = 0;
                        weatherPhase = 1;
                        phaseDuration = rampUpDuration;
                        weatherTimer = 0;
                        return;
                    }
                    return;
                } else {
                    snowTriggered = true;
                    House.clearWeather();
                    House.startWeather(2);
                }
                weatherType = i2;
                lightingEnabled = true;
                z = true;
                if (z) {
                    lightLevel = 1024;
                    flashLevel = 0;
                    weatherPhase = 1;
                    phaseDuration = rampUpDuration;
                    weatherTimer = 0;
                    return;
                }
                return;
            case 1:
                lightLevel = House.interpolate(0, lightFadeDuration, 1024, lightTarget, Math.min(weatherTimer, lightFadeDuration));
                weatherEmitters[0][1] = weatherEmitters[0][0] * House.interpolate(0, phaseDuration, 0, 512, Math.min(weatherTimer, phaseDuration));
                weatherEmitters[1][1] = House.interpolate(0, phaseDuration, 0, 512, Math.min(weatherTimer, phaseDuration)) * weatherEmitters[1][0];
                if (weatherTimer == phaseDuration) {
                    weatherPhase = 2;
                    phaseDuration = peakDuration;
                    weatherTimer = 0;
                    break;
                }
                break;
            case 2:
                int i3;
                int i4;
                int i5;
                int i6;
                int[] iArr;
                int i7;
                int min;
                if (weatherTimer < phaseDuration / 2) {
                    weatherEmitters[0][1] = weatherEmitters[0][0] * House.interpolate(0, phaseDuration / 2, 512, 1024, Math.min(weatherTimer, phaseDuration / 2) + 512);
                    int[] iArr2 = weatherEmitters[1];
                    i3 = weatherEmitters[1][0];
                    i4 = phaseDuration / 2;
                    i5 = 0;
                    i6 = i3;
                    iArr = iArr2;
                    i3 = 512;
                    i7 = i4;
                    i4 = 1024;
                    min = Math.min(weatherTimer, phaseDuration / 2);
                    i2 = 512;
                } else {
                    weatherEmitters[0][1] = weatherEmitters[0][0] * House.interpolate(phaseDuration / 2, phaseDuration, 1024, 512, Math.min(weatherTimer - (phaseDuration / 2), phaseDuration / 2) + 1024);
                    int[] iArr3 = weatherEmitters[1];
                    i7 = weatherEmitters[1][0];
                    i3 = phaseDuration / 2;
                    i4 = phaseDuration;
                    i6 = i7;
                    iArr = iArr3;
                    i7 = i4;
                    i5 = i3;
                    i4 = 512;
                    i3 = 1024;
                    min = Math.min(weatherTimer - (phaseDuration / 2), phaseDuration / 2);
                    i2 = 1024;
                }
                iArr[1] = House.interpolate(i5, i7, i3, i4, i2 + min) * i6;
                if (weatherType == 1) {
                    flashTimer = Math.min(flashTimer + dt, flashDuration);
                    if (House.random(100) == 7) {
                        flashPeak = 512;
                        flashStart = 0;
                        flashTimer = 0;
                        flashLevel = flashPeak;
                    }
                    if (flashLevel > 0) {
                        flashLevel = House.shiftDecay(flashLevel, flashTimer, flashDuration);
                    }
                }
                if (weatherTimer == phaseDuration) {
                    weatherPhase = 3;
                    lightBefore = lightLevel;
                    phaseDuration = rampDownDuration;
                    weatherTimer = 0;
                    break;
                }
                break;
            case 3:
                lightLevel = House.interpolate(0, lightFadeDuration, lightBefore, 1024, Math.min(weatherTimer, lightFadeDuration));
                weatherEmitters[0][1] = weatherEmitters[0][0] * House.interpolate(0, phaseDuration - 1000, 512, 0, Math.min(weatherTimer, phaseDuration - 1000));
                weatherEmitters[1][1] = House.interpolate(0, phaseDuration - 1000, 512, 0, Math.min(weatherTimer, phaseDuration - 1000)) * weatherEmitters[1][0];
                if (weatherTimer == phaseDuration) {
                    weatherPhase = 0;
                    House.clearWeather();
                    return;
                }
                break;
            default:
                return;
        }
        House.updateWeatherParticles(dt);
    }

    private static void startWeather(int weatherType) {
        int[][] iArr;
        int i2;
        if (weatherType == 1) {
            iArr = weatherParams;
            i2 = 0;
        } else {
            iArr = weatherParams;
            i2 = 1;
        }
        int[] iArr2 = iArr[i2];
        rampUpDuration = iArr2[0] + House.random(iArr2[1] + 1);
        peakDuration = iArr2[2] + House.random(iArr2[3] + 1);
        rampDownDuration = iArr2[4] + House.random(iArr2[5] + 1);
        lightBefore = lightLevel;
        lightTarget = iArr2[6];
        lightFadeDuration = iArr2[7];
        int i3 = iArr2[8];
        int i4 = House.random(2) - 1;
        House.spawnEffect(0, 300 - i3, 0, i4 * iArr2[10], iArr2[13], iArr2[11], iArr2[11]);
        House.spawnEffect(1, i3, 0, i4 * iArr2[15], iArr2[18], iArr2[16], iArr2[16]);
    }

    private boolean loadResources() {
        soundPlayer.stopAll();
        if (!House.updateLoadingProgress(0)) {
            return false;
        }
        int i;
        DataInputStream dis = Resources.openStream(89);
        try {
            this.skylineBandCount = dis.readUnsignedByte();
            skylineRects = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.skylineBandCount, 3});
            skylineBounds = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.skylineBandCount, 2});
            this.skylinePaletteSize = dis.readUnsignedByte();
            skylineColors = new int[this.skylinePaletteSize];
            for (i = 0; i < this.skylinePaletteSize; i++) {
                skylineColors[i] = dis.readInt();
            }
            for (i = 0; i < this.skylineBandCount; i++) {
                int readUnsignedByte = dis.readUnsignedByte();
                int readUnsignedByte2 = dis.readUnsignedByte();
                int readUnsignedShort = dis.readUnsignedShort();
                int readUnsignedShort2 = dis.readUnsignedShort();
                skylineRects[i][0] = (byte) ((screenWidth * readUnsignedByte) / 176);
                skylineRects[i][1] = (byte) Math.max(1, (((readUnsignedByte + readUnsignedByte2) * screenWidth) / 176) - skylineRects[i][0]);
                skylineBounds[i][0] = (short) (((readUnsignedShort << 1) * 32) / 32);
                skylineBounds[i][1] = (short) Math.max(1, ((((readUnsignedShort + readUnsignedShort2) << 1) * 32) / 32) - skylineBounds[i][0]);
                skylineRects[i][2] = (byte) dis.readUnsignedByte();
            }
            dis.close();
        } catch (Exception e) {
        }
        if (!House.updateLoadingProgress(10)) {
            return false;
        }
        if (floorMesh == null) {
            floorMesh = Renderer3D.getModel(towerType + 9, -2147483558, true);
            if (!House.updateLoadingProgress(12)) {
                return false;
            }
            fallingMesh = Renderer3D.getModel(towerType + 19, -2147483558, true);
            if (!House.updateLoadingProgress(13)) {
                return false;
            }
            topMesh = Renderer3D.getModel(towerType + 29, -2147483558, true);
            if (!House.updateLoadingProgress(14)) {
                return false;
            }
            topMeshBonus = Renderer3D.getModel(towerType + 39, -2147483558, true);
            if (!House.updateLoadingProgress(16)) {
                return false;
            }
            groundMesh = Renderer3D.getModel(9, -2147483558, true);
            if (!House.updateLoadingProgress(18)) {
                return false;
            }
            craneHookMesh = Renderer3D.getModel(8, -2147483558, true);
            if (!House.updateLoadingProgress(19)) {
                return false;
            }
            craneHookStaticMesh = Renderer3D.getModel(7, -2147483558, true);
        }
        if (!House.updateLoadingProgress(20)) {
            return false;
        }
        Resources.getImage(32);
        if (!House.updateLoadingProgress(30)) {
            return false;
        }
        levelMedals = Resources.getImage(14);
        digitSheetB = Resources.getImage(15);
        digitSheetA = Resources.getImage(16);
        hudIcons = Resources.getImage(18);
        lifeIcons = Resources.getImage(19);
        floorCounterFrame = Resources.getImage(20);
        if (!House.updateLoadingProgress(40)) {
            return false;
        }
        try {
            fenceContainerSprite = Resources.getImage(33);
            fenceChainSprite = Resources.getImage(34);
            fenceWoodSprite = Resources.getImage(35);
            treeSprite = Resources.getImage(36);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!House.updateLoadingProgress(50)) {
            return false;
        }
        flyerFrames = new Image[28];
        int imageIndex = 0;
        while (imageIndex < 28) {
            int[] iArr;
            int i2;
            flyerFrames[imageIndex] = Resources.getImage(flyerImageIds[imageIndex]);
            if (flyerFrames[imageIndex] != null) {
                i = (Math.max(flyerFrames[imageIndex].getHeight(), flyerFrames[imageIndex].getWidth()) * 256) / 32;
                iArr = flyerSizes;
                i2 = imageIndex;
            } else {
                i2 = imageIndex;
                iArr = flyerSizes;
                i = 1;
            }
            iArr[i2] = i;
            if (imageIndex == 5 || imageIndex == 11) {
                int[] iArr2 = flyerSizes;
                iArr2[imageIndex] = iArr2[imageIndex] / 2;
            }
            imageIndex++;
        }
        if (!House.updateLoadingProgress(60)) {
            return false;
        }
        sparkSprite = Resources.getImage(37);
        if (!House.updateLoadingProgress(70)) {
            return false;
        }
        fenceHeight = fenceContainerSprite.getHeight();
        fenceContainerWidth = fenceContainerSprite.getWidth();
        fenceWoodWidth = fenceWoodSprite.getWidth();
        fenceChainWidth = fenceChainSprite.getWidth();
        treeWidth = treeSprite.getWidth();
        craneArmSprite = Resources.getImage(41);
        comboStarSprite = Resources.getImage(38);
        comboSparkleSprite = Resources.getImage(39);
        if (!House.updateLoadingProgress(80)) {
            return false;
        }
        waterSplashSprite = Resources.getImage(40);
        System.gc();
        resetTiming();
        return House.updateLoadingProgress(100);
    }

    private static void unloadModels() {
        floorMesh = null;
        topMesh = null;
        fallingMesh = null;
        topMeshBonus = null;
        groundMesh = null;
        craneHookMesh = null;
        craneHookStaticMesh = null;
        skylineRects = (byte[][]) null;
        skylineBounds = (short[][]) null;
        skylineColors = null;
        levelMedals = null;
        digitSheetB = null;
        digitSheetA = null;
        hudIcons = null;
        lifeIcons = null;
        floorCounterFrame = null;
        fenceContainerSprite = null;
        fenceChainSprite = null;
        fenceWoodSprite = null;
        treeSprite = null;
        sparkSprite = null;
        System.gc();
        waterSplashSprite = null;
    }

    private static void updateWeatherParticles(int dt) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 2) {
            int[] iArr = weatherEmitters[i2];
            iArr[2] = iArr[2] + ((((iArr[1] * iArr[4]) / 2) / weatherAreaHeight) * dt);
            for (int i4 = 0; i4 < iArr[0]; i4++) {
                int[] iArr3 = weatherParticles[i4 + i3];
                if (iArr3[0] == 1) {
                    int i5 = ((camY * 256) >> 8) + 384;
                    iArr3[1] = iArr3[1] + (iArr3[5] * dt);
                    iArr3[2] = iArr3[2] + (iArr3[6] * dt);
                    switch (weatherType) {
                        case 2:
                            iArr3[4] = iArr3[4] + ((((3 - iArr3[3]) * 90) + 90) * dt);
                            iArr3[4] = iArr3[4] % 368640;
                            iArr3[1] = iArr3[1] + (((iArr3[3] * 64) * House.sin(iArr3[4] >> 10)) >> 15);
                            break;
                    }
                    if (iArr3[2] > weatherAreaHeight) {
                        iArr3[0] = 0;
                    } else if (i5 < 1024 && iArr3[2] > (i5 * weatherAreaHeight) / 1024) {
                        iArr3[0] = 0;
                    } else if (iArr3[1] < 0) {
                        iArr3[1] = weatherAreaWidth;
                    } else if (iArr3[1] > weatherAreaWidth) {
                        iArr3[1] = 0;
                    }
                } else if (iArr[2] >= 1024) {
                    int i5 = iArr[5] + 1;
                    int i7 = iArr[6] + 1;
                    iArr3[0] = 1;
                    iArr3[1] = House.random(weatherAreaWidth);
                    iArr3[2] = 0;
                    iArr3[3] = House.random(4);
                    iArr3[4] = House.random(368640);
                    iArr3[5] = ((iArr[3] + House.random(i5)) - (i5 / 2)) / (4 - iArr3[3]);
                    iArr3[6] = ((iArr[4] + House.random(i7)) - (i7 / 2)) / (4 - iArr3[3]);
                    iArr[2] = iArr[2] - 1024;
                }
            }
            i2++;
            i3 += iArr[0];
        }
    }

    private void initScreenLayout() {
        int i;
        centerX = screenWidth >> 1;
        centerY = (screenHeight >> 1) + 0;
        viewWidthUnits = (screenWidth * 256) / 32;
        viewHeightUnits = (screenHeight * 256) / 32;
        loadingImage = Resources.getImage(9);
        arrowSprite = Resources.getImage(0);
        if (citizenSpriteA == null) {
            citizenSpriteA = Resources.getImage(12);
        }
        if (citizenSpriteB == null) {
            citizenSpriteB = Resources.getImage(13);
        }
        if (cloudLargeSprite == null) {
            cloudLargeSprite = Resources.getImage(69);
        }
        if (cloudSmallSprite == null) {
            cloudSmallSprite = Resources.getImage(70);
        }
        if (flyerFrames == null) {
            flyerFrames = new Image[28];
        }
        if (flyerFrames[3] == null) {
            flyerFrames[3] = Resources.getImage(45);
        }
        skyStage = -1;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i <= 6; i++) {
            jumpArcX[i] = i2;
            jumpArcY[i] = i3;
            i2 += i + 5;
            i3 += 5 - i;
        }
        hudX = screenWidth / 20;
        hudHeight = screenHeight / 15;
        this.skylineSegmentCount = 24;
        this.skylineSegmentCount = 13;
        skylineSegments = (short[][]) Array.newInstance(Short.TYPE, new int[]{2, this.skylineSegmentCount});
        for (i = 0; i < this.skylineSegmentCount; i++) {
            short s = (short) ((i / 5) * 45);
            skylineSegments[0][i] = (short) ((i % 5) * 45);
            skylineSegments[1][i] = s;
        }
        camZ = 128;
        for (i = screenHeight; i > 48; i = (int) (Renderer3D.projected[1] - (0.5f * ((float) screenHeight)))) {
            camZ += 100;
            Renderer3D.lookAt(0.0f, 0.0f, (float) camZ, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
            Renderer3D.projected[0] = 0.0f;
            Renderer3D.projected[1] = -384.0f;
            Renderer3D.projected[2] = 128.0f;
            Renderer3D.projected[3] = 1.0f;
            Renderer3D.project(Renderer3D.projected);
        }
        skyStageLength = Math.max(viewHeightUnits, 2048);
        scoreThresholds = new int[]{70, 250, 550, 1000};
        onLoad();
        if (House.hasSavedGame(3)) {
            hasCitySave = true;
        }
        if (House.hasSavedGame(1)) {
            hasQuickSave = true;
        }
    }

    private static void spawnFlyer(int flyerType) {
        int i2 = 0;
        int i3 = 0;
        while (i3 <= 28) {
            if (skyStage >= flyerTable[0][i3] && skyStage < flyerTable[1][i3] && ((flyerCounts[i3] > 0 || flyerCounts[i3] == -1) && House.random(100) < flyerTable[2][i3])) {
                i2 = i3;
                break;
            }
            i3++;
        }
        if (i2 == 0) {
            flyers[flyerType][0] = i2;
            flyers[flyerType][3] = (gameTime + 1000) + House.random(2500);
            return;
        }
        int[] iArr;
        int i4;
        int[] iArr2 = flyerCounts;
        iArr2[i2] = iArr2[i2] - 1;
        if (flyerTable[3][i2] == 0 || House.random(2) == 0) {
            flyers[flyerType][1] = House.random(viewWidthUnits);
            iArr = flyers[flyerType];
            i4 = flyerSizes[i2 - 1] + ((camY * 3) / 4);
            i3 = 512;
        } else {
            if (flyerTable[3][i2] > 0) {
                i3 = 0 - flyerSizes[i2 - 1];
            } else {
                i3 = viewWidthUnits + flyerSizes[i2 - 1];
            }
            flyers[flyerType][1] = i3;
            iArr = flyers[flyerType];
            i4 = (((camY * 3) / 4) - (viewHeightUnits >> 1)) - 512;
            i3 = 1024;
        }
        iArr[2] = House.random(i3) + i4;
        flyers[flyerType][0] = i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /**
     * Eases the camera height aW towards its target aX and applies the landing shake
     * Updates camera position and target interpolation.
     */
    private static void updateCamera() {
        if (camY < camTargetY) {
            camY = Math.min(camTargetY, camTargetY + ((((gameTime - camEaseStart) - 500) * 256) / 500));
            if (phase != PHASE_ROOF && phase != PHASE_INTRO) {
                pivotY = camY + 1792 + 128;
            }
        } else if (camY > camTargetY) {
            camY = Math.max(camTargetY, camTargetY - ((((gameTime - camEaseStart) - 500) * 256) / 500));
            skylineStart = 0;
            if (phase != PHASE_ROOF && phase != PHASE_INTRO) {
                pivotY = camY + 1792 + 128;
            }
        }
        if (gameTime - lastHitTime < 800) {
            camY = (camY + 32) - House.random(64);
        }
    }

    private static void addScore(int points) {
        int i2;
        if (phase == PHASE_ROOF) {
            i2 = score + points;
        } else if (points > 0) {
            if (comboTimer > 0) {
                comboBonus += comboCount * (((blockCount / 10) * 2) + 2);
            }
            i2 = score + ((blockCount / 10) + points);
        } else {
            i2 = score - ((blockCount / 10) - points);
        }
        score = i2;
    }

    private static void layoutTower() {
        int i = scrolledOffsetSum + swayOffset;
        int max = Math.max(0, blockCount - 5);
        int i2 = ((max - 1) * 256) + 128;
        int i3 = 0;
        int i4 = 0;
        while (max < blockCount) {
            int i5;
            int i6;
            int[] iArr;
            byte b = landingOffsets[max % 20];
            if (swayCos > 0) {
                if (b < (byte) 0) {
                    i5 = (towerInstability * b) * swayCos;
                    i6 = 29491200;
                } else {
                    i6 = -((towerInstability * b) * swayCos);
                    i5 = i6;
                    i6 = 58982400;
                }
            } else if (b > (byte) 0) {
                i5 = -((towerInstability * b) * swayCos);
                i6 = 29491200;
            } else {
                i6 = (towerInstability * b) * swayCos;
                i5 = i6;
                i6 = 58982400;
            }
            i6 = i5 / i6;
            if (max == blockCount - 1 && max != blockGoal - 1) {
                i5 = gameTime - lastLandTime;
                if (i5 < 100) {
                    i3 = ((i3 / 8) + (b / 6)) + ((i5 * b) / 600);
                } else if (i5 < 500) {
                    i3 = ((i3 / 8) + (b / 6)) + (((400 - (i5 - 100)) * b) / 2400);
                    landingBounce = i3;
                } else if (i5 < 800) {
                    i3 = landingBounce - (((landingBounce - i3) * (i5 - 500)) / 300);
                }
            }
            i3 += i6;
            int i7 = swayCos > 0 ? i3 >> 1 : -(i3 >> 1);
            if (max == 0) {
                i6 = -999;
                iArr = floorAngle;
                i5 = 0;
            } else if (phase == PHASE_ROUND_OVER && max == blockCount - 1 && landingQuality != 0) {
                iArr = floorAngle;
                i5 = 4;
                i6 = -888;
            } else {
                i5 = i4;
                iArr = floorAngle;
                i6 = i3;
            }
            iArr[i5] = i6;
            i += (i3 * 2) + b;
            floorX[i4] = i;
            floorY[i4] = (i2 + i7) + 256;
            i2 = floorY[i4];
            i4++;
            max++;
        }
    }

    private static void changeFloorCount(int delta) {
        int abs;
        int i2;
        int i3 = 128;
        if (delta < 0) {
            abs = Math.abs(landingOffsets[(blockCount - 1) % 20]);
            abs = abs < 25 ? -4 : abs < 50 ? -3 : abs < 80 ? -2 : -1;
            House.addScore(abs);
        }
        if (delta > 0 && blockCount > 4) {
            scrolledOffsetSum += landingOffsets[bottomFloor % 20];
        } else if (delta < 0) {
            offsetSum -= landingOffsets[(blockCount - 1) % 20];
        }
        blockCount += delta;
        bottomFloor = Math.max(0, blockCount - 5);
        topFloorSlot = Math.min(4, blockCount - 1);
        if (delta < 0) {
            scrolledOffsetSum -= landingOffsets[bottomFloor % 20];
        }
        towerInstability = 0;
        for (abs = bottomFloor; abs < blockCount; abs++) {
            towerInstability += Math.abs(landingOffsets[abs % 20]);
        }
        towerInstability /= 5;
        towerInstability = Math.min((blockCount * towerInstability) / 20, 100);
        swayAmplitude = Math.min((blockCount / 2) + (Math.abs(offsetSum) / 20), (blockCount * ((blockCount / 2) + (Math.abs(offsetSum) / 20))) / 6);
        if (gameMode == GAME_MODE_CITY_TOWER) {
            i2 = Math.max(1, swingPeriod);
            abs = towerType;
            swingPeriod = swingPeriodTable[abs] - ((blockCount * (swingPeriodTable[abs] - swingPeriodTable[abs + 2])) / goalByTowerType[towerType - 1]);
            swingAmplitudeX = Math.min(swingAmplitudeXByType[abs], swingAmplitudeXByType[1] + ((blockCount * (swingAmplitudeXByType[abs] - swingAmplitudeXByType[1])) / (blockGoal >> 1)));
            swingAmplitudeY = Math.min(swingAmplitudeYByType[abs], (((swingAmplitudeYByType[abs] - swingAmplitudeYByType[1]) * blockCount) / (blockGoal >> 1)) + swingAmplitudeYByType[1]);
            abs = (blockCount * 256) / 100;
        } else {
            i2 = Math.max(1, swingPeriod);
            swingAmplitudeX = Math.min(swingAmplitudeXByType[towerType], swingAmplitudeXByType[0] + ((blockCount * (swingAmplitudeXByType[towerType] - swingAmplitudeXByType[0])) / 60));
            swingAmplitudeY = Math.min(swingAmplitudeYByType[towerType], swingAmplitudeYByType[0] + ((blockCount * (swingAmplitudeYByType[towerType] - swingAmplitudeYByType[0])) / 60));
            if (blockCount < 100) {
                craneYOffset = -Math.min(128, (blockCount * 256) / 200);
                swingPeriod = Math.max(swingPeriodTable[towerType + 2], swingPeriodTable[0] - ((blockCount * (swingPeriodTable[0] - swingPeriodTable[towerType + 2])) / 100));
                craneTime = (craneTime * swingPeriod) / Math.max(1, i2);
                if (phase != PHASE_ROOF && phase != PHASE_INTRO) {
                    House.raiseCamera(delta * 256);
                    return;
                }
            }
            swingPeriod = swingPeriodTable[towerType + 1] - (((blockCount - 100) * 100) / 150);
            i3 = 256;
            abs = (((blockCount - 100) * 256) / 300) + 128;
        }
        craneYOffset = -Math.min(i3, abs);
        craneTime = (craneTime * swingPeriod) / Math.max(1, i2);
        if (phase != PHASE_ROOF && phase != PHASE_INTRO) {
            House.raiseCamera(delta * 256);
        }
    }

    protected final void onInit() {
        House.initSinTable();
        String[] strArr = new String[]{this.canvas.getKeyName(52), this.canvas.getKeyName(54), this.canvas.getKeyName(50), this.canvas.getKeyName(56), this.canvas.getKeyName(53)};
    }

    public final void update(int delta, int totalTime) {
        if (this.pendingModeChange == 0) {
            int i3 = 0;
            if (screenState == 7) {
                if (hasCitySave || hasQuickSave) {
                    floorMesh = null;
                    topMesh = null;
                    fallingMesh = null;
                    topMeshBonus = null;
                    groundMesh = null;
                    craneHookMesh = null;
                    craneHookStaticMesh = null;
                }
                if (nextScreenState == 2) {
                    House.updateLoadingProgress(5);
                    if (cityInitPending) {
                        cityInitPending = false;
                        if (!cityInitialized) {
                            CityMode.init();
                            House.updateLoadingProgress(10);
                            cityInitialized = true;
                        }
                        if (!CityMode.towerGameActive) {
                            CityMode.enterCity();
                        }
                    }
                    House.unloadModels();
                    House.updateLoadingProgress(15);
                    if (CityMode.loadAssets()) {
                        resetTiming();
                        this.sound.play(-2147483566, -1);
                        screenState = nextScreenState;
                        roundFinishedCity = false;
                        roundFinishedQuick = false;
                    } else {
                        return;
                    }
                }
                if (nextScreenState != 2) {
                CityMode.unloadAssets();
                if (loadResources()) {
                    skyStage = -1;
                    resetTiming();
                    if (!(roundFinishedCity && roundFinishedQuick)) {
                        this.sound.play(-2147483567, -1);
                    }
                    if (gameMode == GAME_MODE_QUICK) {
                        if (roundFinishedQuick) {
                            if (!newRecord) {
                                soundPlayer.play(-2147483564, 1);
                            }
                            if (newRecord) {
                                soundPlayer.play(-2147483563, 1);
                            }
                        }
                        if (progress[1] == 0) {
                            House.showPrompt(Resources.getString(34, new String[]{"3"}), null, null);
                            i3 = 4;
                        } else {
                            i3 = nextScreenState;
                        }
                    } else {
                        if (roundFinishedCity) {
                            SoundPlayer oVar;
                            if (landingQuality == 0) {
                                oVar = soundPlayer;
                                i3 = -2147483565;
                            } else if (landingQuality == 2) {
                                oVar = soundPlayer;
                                i3 = -2147483563;
                            } else {
                                oVar = soundPlayer;
                                i3 = -2147483564;
                            }
                            oVar.play(i3, 1);
                        }
                        if (progress[0] == 0) {
                            House.showPrompt(Resources.getString(55, new String[]{"3"}), null, null);
                            i3 = 4;
                            phase = PHASE_INTRO;
                        } else {
                            i3 = nextScreenState;
                        }
                    }
                    screenState = i3;
                } else {
                    return;
                }
                }
            }
            i3 = delta > 150 ? 150 : delta;
            if (screenState == 2) {
                gameTime += i3;
                CityMode.update(i3, delta);
                if (CityMode.consumeHighScoreFlag() && !CityMode.isCheatActive()) {
                    this.highScores.submitScore(0, new int[]{CityMode.getPopulation(), 0}, "-");
                }
                if (CityMode.isTowerGameActive()) {
                    boolean z;
                    towerType = CityMode.getBuildingTowerType();
                    if (towerType > 4) {
                        towerType -= 4;
                        z = true;
                    } else {
                        z = false;
                    }
                    bonusEligible = z;
                    blockGoal = goalByTowerType[towerType - 1];
                    House.initArrays();
                    screenState = 7;
                    nextScreenState = 1;
                    return;
                }
                return;
            }
            handleInput();
            if (phase == PHASE_ROUND_OVER && gameTime - fallStartTime > 2000) {
                if (gameMode == GAME_MODE_CITY_TOWER) {
                    roundFinishedCity = true;
                    soundPlayer.stopAll();
                    if (landingQuality == 0) {
                        soundPlayer.play(-2147483565, 1);
                        House.showPrompt(Resources.getString(56), null, null);
                        screenState = 5;
                    } else if (landingQuality == 2) {
                        soundPlayer.play(-2147483563, 1);
                        House.showPrompt(Resources.getString(57), null, null);
                        screenState = 5;
                    } else {
                        soundPlayer.play(-2147483564, 1);
                        buildResultText(false);
                    }
                } else {
                    roundFinishedQuick = true;
                    soundPlayer.stopAll();
                    buildResultText(true);
                    if (!newRecord) {
                        soundPlayer.play(-2147483564, 1);
                    }
                    if (newRecord) {
                        soundPlayer.play(-2147483563, 1);
                    }
                }
                phase = PHASE_RESULT;
            }
            this.physicsAccumulatorMs = i3 + this.physicsAccumulatorMs;
            if (this.physicsAccumulatorMs >= 25) {
                gameTime += this.physicsAccumulatorMs;
                if (screenState == 4 || screenState == 6 || screenState == 5 || screenState == 8) {
                    this.physicsAccumulatorMs = 0;
                    return;
                }
                House.updateCamera();
                House.updateWeather(this.physicsAccumulatorMs);
                House.updateFlyers();
                House.updateCrane(this.physicsAccumulatorMs);
                House.updateBlockPhysics(this.physicsAccumulatorMs);
                House.updateTowerSway(this.physicsAccumulatorMs);
                House.layoutTower();
                House.updateCitizens();
                if (comboTimer > 0) {
                    comboTimer = (comboTimer - this.physicsAccumulatorMs) - (((comboCount - 1) * this.physicsAccumulatorMs) / 6);
                    if (comboTimer <= 0) {
                        House.cashInCombo();
                    }
                } else if (comboTimer > -2000) {
                    comboTimer -= this.physicsAccumulatorMs;
                }
                this.physicsAccumulatorMs = 0;
            }
        }
    }

    public final void commandAction(Command command) {
        if (command == this.softKeyCommand) {
            if (screenState == 7) {
                loadCancelled = true;
            }
            this.pendingModeChange = 2;
            this.soundEnabled = false;
        }
    }

    protected final void paintLoading(Graphics g) {
        if (this.loadingNeedsRepaint) {
            Image image = null;
            if (this.loadingScreenType == 0) {
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                g.setColor(-1);
                g.fillRect(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                if (this.splashImage != null) {
                    image = this.splashImage;
                }
                this.loadingNeedsRepaint = false;
            }
            if (this.loadingScreenType == 1) {
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                g.setColor(10143978);
                g.fillRect(0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                if (cloudLargeSprite == null) {
                    cloudLargeSprite = Resources.getImage(69);
                }
                if (cloudSmallSprite == null) {
                    cloudSmallSprite = Resources.getImage(70);
                }
                if (citizenSpriteA == null) {
                    citizenSpriteA = Resources.getImage(12);
                }
                if (citizenSpriteB == null) {
                    citizenSpriteB = Resources.getImage(13);
                }
                if (flyerFrames == null) {
                    flyerFrames = new Image[28];
                    if (flyerFrames[3] == null) {
                        flyerFrames[3] = Resources.getImage(45);
                    }
                }
                House.setClipRect(g, screenWidth >> 1, 30, flyerFrames[3].getWidth(), flyerFrames[3].getHeight());
                g.drawImage(flyerFrames[3], screenWidth >> 1, 30, 20);
                House.setClipRect(g, 0, 0, screenWidth, screenHeight);
                House.setClipRect(g, 10, 40, cloudSmallSprite.getWidth(), cloudSmallSprite.getHeight());
                g.drawImage(cloudSmallSprite, 10, 40, 20);
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.setClipRect(g, screenWidth - 80, 60, cloudLargeSprite.getWidth(), cloudLargeSprite.getHeight());
                g.drawImage(cloudLargeSprite, screenWidth - 80, 60, 20);
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.drawCitizen(g, 20, screenHeight - 40, 4, 1, 1);
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.drawCitizen(g, 80, screenHeight - 50, 1, -1, 0);
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.drawCitizen(g, 20, 50, 2, -1, 0);
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                House.setClipRect(g, screenWidth - 60, screenHeight - 40, cloudSmallSprite.getWidth(), cloudSmallSprite.getHeight());
                g.drawImage(cloudSmallSprite, screenWidth - 60, screenHeight - 40, 20);
                House.setClipRect(g, 0, 0, GameMIDlet.screenWidth, GameMIDlet.screenHeight);
                if (this.titleLogoImage != null) {
                    image = this.titleLogoImage;
                }
            }
            this.loadingNeedsRepaint = false;
            if (image != null) {
                g.drawImage(image, GameMIDlet.screenWidth >> 1, GameMIDlet.screenHeight >> 1, 3);
            }
            this.loadingNeedsRepaint = false;
        }
    }

    public final void paint(Graphics g, boolean fullRedraw) {
        if (this.pendingModeChange == 0) {
            if (screenState == 7) {
                House.paintLoadingScreen(g);
                return;
            }
            g.setFont(gameFont);
            if (screenState == 2) {
                if (CityMode.assetsLoaded) {
                    CityMode.paint(g);
                } else if (loadingImage != null) {
                    House.paintLoadingScreen(g);
                } else {
                    g.setColor(0);
                    g.fillRect(0, 0, screenWidth, screenHeight);
                }
                return;
            }
            if (skylineRects == null || skylineBounds == null || clouds == null || flyers == null) {
                // Tower resources are released while loading city mode: show loading bar instead
                if (loadingImage != null) {
                    House.paintLoadingScreen(g);
                } else {
                    g.setColor(0);
                    g.fillRect(0, 0, screenWidth, screenHeight);
                }
                return;
            }
            House.paintScenery(g);
            House.paintWeatherLayer(g, false);
            if (phase == PHASE_ROOF) {
                blockAngle[0] = 888;
            }
            if (phase == PHASE_INTRO) {
                blockAngle[0] = 999;
            }
            render3D(g);
            House.paintWeatherLayer(g, true);
            House.paintPerfectEffect(g);
            House.paintCitizens(g);
            if (blockCount > 5) {
                for (int i = 0; i < 5; i++) {
                    if (gameTime - lostBlocks[1][i] < 300) {
                        int i2 = lostBlocks[0][i];
                        int i3 = (gameTime - lostBlocks[1][i]) / 100;
                        House.setClipRect(g, i2 - 11, screenHeight - 34, 23, 34);
                        g.drawImage(waterSplashSprite, (i2 - 11) - (i3 * 23), screenHeight - 34, 20);
                    }
                }
            }
            House.paintHud(g);
            if (screenState == 4 || screenState == 6 || screenState == 5 || screenState == 8) {
                House.paintTutorialModal(g);
            }
        }
    }

    protected final void onPause() {
        loadCancelled = true;
        this.pendingModeChange = 2;
    }

    public final void clearSavedGame(int saveSlotOrGameMode) {
        int i2 = 0;
        if (saveSlotOrGameMode == 3) {
            i2 = 8;
        } else if (saveSlotOrGameMode == 1) {
            i2 = 9;
            saveSettings();
        }
        Storage.setMenuFlag(i2, 1);
        Storage.setSetting(10, 0);
        saveSettings();
    }

    public final void keyPressed(int keyCode, int gameAction) {
        if (screenState == 2) {
            CityMode.handleKeyPressed(keyCode, gameAction);
            return;
        }
        if (keyCode == 35 && CityMode.isCheatActive()) {
            phase = PHASE_ROUND_OVER;
        }
        if (this.loadingScreenType != -1) {
            this.loadingKeyPressed = true;
        } else if (screenState != 7) {
            int e = House.mapKey(keyCode, gameAction);
            if (e == 0) {
                inputSelect = true;
            } else if (e == 1) {
                inputUp = true;
            } else if (e == 2) {
                inputDown = true;
            }
        }
    }

    protected final void paintBackground(Graphics g) {
        int i;
        int i2;
        int i3;
        if (flyerFrames[3] == null) {
            flyerFrames[3] = Resources.getImage(45);
        }
        if (menuCloudInit == null) {
            menuCloudInit = new int[20];
            for (i = 0; i < menuCloudInit.length / 5; i++) {
                menuCloudInit[(i * 5) + 1] = viewHeightUnits;
            }
        }
        House.paintSky(g, (loadingScroll * 32) >> 8, true, false);
        for (i = 0; i < menuClouds.length / 5; i++) {
            if (menuClouds[(i * 5) + 4] == 0) {
                i2 = (menuClouds[(i * 5) + 0] * 32) >> 8;
                i3 = (menuClouds[(i * 5) + 1] * 32) >> 8;
                House.setClipRect(g, i2, i3, cloudSmallSprite.getWidth(), cloudSmallSprite.getHeight());
                g.drawImage(cloudSmallSprite, i2, i3, 20);
            }
        }
        for (i = 0; i < menuClouds.length / 5; i++) {
            if (menuClouds[(i * 5) + 4] == 1) {
                i2 = (menuClouds[(i * 5) + 0] * 32) >> 8;
                i3 = (menuClouds[(i * 5) + 1] * 32) >> 8;
                House.setClipRect(g, i2, i3, cloudLargeSprite.getWidth(), cloudLargeSprite.getHeight());
                g.drawImage(cloudLargeSprite, i2, i3, 20);
            }
        }
        for (int i4 = 0; i4 < menuCitizens.length / 6; i4++) {
            i2 = (menuCitizens[(i4 * 6) + 0] * 32) >> 8;
            i3 = (menuCitizens[(i4 * 6) + 1] * 32) >> 8;
            int i5 = menuCitizens[(i4 * 6) + 2] + 1;
            if (i5 > 5) {
                i5 = 5 - (i5 - 5);
            }
            House.drawCitizen(g, i2, i3, i5, menuCitizens[(i4 * 6) + 3] < 0 ? -1 : 1, menuCitizens[(i4 * 6) + 5]);
        }
        for (i = 0; i < menuCloudInit.length / 5; i++) {
            i2 = (menuCloudInit[(i * 5) + 0] * 32) >> 8;
            i3 = (screenHeight - (menuCloudInit[(i * 5) + 1] * 32)) >> 8;
            House.setClipRect(g, i2, i3, flyerFrames[3].getWidth(), flyerFrames[3].getHeight());
            g.drawImage(flyerFrames[3], i2, i3, 20);
            House.setClipRect(g, 0, 0, screenWidth, screenHeight);
        }
    }

    protected final void saveGame() {
        saveSettings();
        if (gameMode == 2) {
            if (!CityMode.isCheatActive()) {
                this.highScores.submitScore(1, new int[]{score, blockCount}, MenuController.getPlayerName());
            }
        } else if (gameMode == GAME_MODE_CITY_TOWER) {
            CityMode.saveState();
            House.saveTowerCityMode();
        } else {
            House.saveTowerQuickMode();
        }
    }

    public final void markSavedGame(int saveSlotOrGameMode) {
        int i2 = 9;
        if (saveSlotOrGameMode == 3) {
            Storage.setMenuFlag(8, 0);
        } else {
            if (saveSlotOrGameMode == 1) {
                Storage.setMenuFlag(9, 0);
                i2 = 10;
            }
            saveSettings();
        }
        Storage.setSetting(i2, 1);
        saveSettings();
    }

    protected final void saveSettings() {
        if (!CityMode.isCheatActive()) {
            try {
                DataOutputStream dos = Storage.openWrite("towermode");
                for (int i = 0; i < 6; i++) {
                    dos.writeInt(progress[i]);
                }
                dos.writeBoolean(House.hasSavedGame(1));
                dos.writeBoolean(House.hasSavedGame(3));
                Storage.close();
            } catch (Exception e) {
            }
        }
    }

    protected final boolean loadStep(int step) {
        boolean z = false;
        if (this.loadingScreenType == -1) {
            this.loadingScreenType = 1;
            this.loadingTimeoutMs = step + 3000;
            this.loadingNeedsRepaint = true;
        }
        if (this.loadingScreenType != 1) {
            return true;
        }
        if (this.loadingTimeoutMs < step || this.loadingKeyPressed) {
            initScreenLayout();
            this.loadingScreenType = -1;
            this.loadingKeyPressed = false;
        } else {
            z = true;
        }
        if (this.soundEnabled && !this.titleBgmStarted) {
            this.sound.play(-2147483568, -1);
            this.titleBgmStarted = true;
        }
        this.loadingNeedsRepaint = true;
        return z;
    }

    protected final void onLoad() {
        int i = 1;
        progress = new int[6];
        Storage.setMenuFlag(9, 1);
        Storage.setSetting(10, 0);
        Storage.setMenuFlag(8, 1);
        Storage.setSetting(9, 0);
        try {
            int i2;
            int i3;
            int i4;
            DataInputStream dis = Storage.openRead("towermode");
            for (i2 = 0; i2 < 6; i2++) {
                progress[i2] = dis.readInt();
            }
            if (dis.readBoolean()) {
                Storage.setMenuFlag(9, 0);
                i2 = 10;
                i3 = 1;
            } else {
                Storage.setMenuFlag(9, 1);
                i2 = 10;
                i3 = 0;
            }
            Storage.setSetting(i2, i3);
            if (dis.readBoolean()) {
                Storage.setMenuFlag(8, 0);
                i4 = 9;
            } else {
                Storage.setMenuFlag(8, 1);
                i = 0;
                i4 = 9;
            }
            Storage.setSetting(i4, i);
            Storage.close();
        } catch (Exception e) {
        }
    }

    public final void keyReleasedRaw(int keyCode) {
        super.keyReleasedRaw(keyCode);
    }

    public final String formatNumber(int number) {
        return new StringBuffer().append("").append(number).toString();
    }

    protected final void loadTowerQuickMode() {
        int i = 0;
        try {
            int i2;
            int i3;
            DataInputStream dis = Storage.openRead("quickModeRS");
            gameMode = dis.readInt();
            screenState = dis.readInt();
            phase = dis.readInt();
            gameTime = dis.readInt();
            centerX = dis.readInt();
            centerY = dis.readInt();
            camX = dis.readInt();
            camY = dis.readInt();
            camZ = dis.readInt();
            camTargetY = dis.readInt();
            camEaseStart = dis.readInt();
            pivotX = dis.readInt();
            pivotY = dis.readInt();
            hookX = dis.readInt();
            hookY = dis.readInt();
            swingAngle = dis.readInt();
            craneTime = dis.readInt();
            craneYOffset = dis.readInt();
            ropeLength = dis.readInt();
            swingPeriod = dis.readInt();
            prevHookX = dis.readInt();
            prevHookY = dis.readInt();
            swingAmplitudeX = dis.readInt();
            swingAmplitudeY = dis.readInt();
            skyStage = dis.readInt();
            fenceHeight = dis.readInt();
            fenceContainerWidth = dis.readInt();
            fenceChainWidth = dis.readInt();
            fenceWoodWidth = dis.readInt();
            skyStageLength = dis.readInt();
            blockCount = dis.readInt();
            blockGoal = dis.readInt();
            towerType = dis.readInt();
            foundationOffset = dis.readInt();
            unusedValue = dis.readInt();
            scrolledOffsetSum = dis.readInt();
            offsetSum = dis.readInt();
            topFloorSlot = dis.readInt();
            swayAngle = dis.readInt();
            swayAmplitude = dis.readInt();
            swayPhase = dis.readInt();
            for (i2 = 0; i2 < floorX.length; i2++) {
                floorX[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < floorY.length; i2++) {
                floorY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < floorSway.length; i2++) {
                floorSway[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < floorAngle.length; i2++) {
                floorAngle[i2] = dis.readInt();
            }
            bonusEligible = dis.readBoolean();
            floorIndex = dis.readInt();
            landingQuality = dis.readInt();
            for (i2 = 0; i2 < blockX.length; i2++) {
                blockX[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockY.length; i2++) {
                blockY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockState.length; i2++) {
                blockState[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockTilt.length; i2++) {
                blockTilt[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockAngle.length; i2++) {
                blockAngle[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockVelX.length; i2++) {
                blockVelX[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockVelY.length; i2++) {
                blockVelY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockStartY.length; i2++) {
                blockStartY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockTargetAngle.length; i2++) {
                blockTargetAngle[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < landingOffsets.length; i2++) {
                landingOffsets[i2] = dis.readByte();
            }
            for (i2 = 0; i2 < blockTargetTilt.length; i2++) {
                blockTargetTilt[i2] = dis.readInt();
            }
            for (i3 = 0; i3 < 8; i3++) {
                for (i2 = 0; i2 < 8; i2++) {
                    citizens[i3][i2] = dis.readInt();
                }
            }
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 5; i2++) {
                    lostBlocks[i3][i2] = dis.readInt();
                }
            }
            hudX = dis.readInt();
            hudHeight = dis.readInt();
            score = dis.readInt();
            lives = dis.readInt();
            lastLossAmount = dis.readInt();
            lastLossTime = dis.readInt();
            lastPerfectTime = dis.readInt();
            comboTimer = dis.readInt();
            comboBonus = dis.readInt();
            comboCount = dis.readInt();
            lastLandTime = dis.readInt();
            skylineStart = dis.readInt();
            for (i3 = 0; i3 < 12; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    clouds[i3][i2] = dis.readInt();
                }
            }
            for (i3 = 0; i3 < 9; i3++) {
                for (i2 = 0; i2 < 6; i2++) {
                    flyers[i3][i2] = dis.readInt();
                }
            }
            for (i2 = 0; i2 < progress.length; i2++) {
                progress[i2] = dis.readInt();
            }
            roundFinishedQuick = dis.readBoolean();
            maxCombo = dis.readInt();
            perfectTipActiveQuick = dis.readBoolean();
            newRecord = dis.readBoolean();
            raining = dis.readBoolean();
            snowing = dis.readBoolean();
            reservedFlagA = dis.readBoolean();
            rainTriggered = dis.readBoolean();
            snowTriggered = dis.readBoolean();
            lightingEnabled = dis.readBoolean();
            reservedFlagB = dis.readBoolean();
            lightLevel = dis.readInt();
            lightBefore = dis.readInt();
            lightTarget = dis.readInt();
            lightFadeDuration = dis.readInt();
            flashLevel = dis.readInt();
            flashPeak = dis.readInt();
            flashStart = dis.readInt();
            flashTimer = dis.readInt();
            flashDuration = dis.readInt();
            reservedStateFlag = dis.readInt();
            weatherPhase = dis.readInt();
            phaseDuration = dis.readInt();
            rampUpDuration = dis.readInt();
            peakDuration = dis.readInt();
            rampDownDuration = dis.readInt();
            weatherTimer = dis.readInt();
            weatherType = dis.readInt();
            reservedValue = dis.readInt();
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    weatherEmitters[i3][i2] = dis.readInt();
                }
            }
            while (i < 2) {
                int[] iArr = weatherEmitters[i];
                iArr[0] = weatherEmitters[i][0];
                iArr[1] = weatherEmitters[i][1];
                iArr[2] = weatherEmitters[i][2];
                iArr[3] = weatherEmitters[i][3];
                iArr[4] = weatherEmitters[i][4];
                iArr[5] = weatherEmitters[i][5];
                iArr[6] = weatherEmitters[i][6];
                i++;
            }
            lastHitTime = dis.readInt();
            Storage.close();
            if (blockState[0] == BLOCK_LANDED) {
                ropeLength = 1664;
                if (phase != PHASE_ROUND_OVER) {
                    blockState[0] = BLOCK_HANGING;
                    blockX[0] = hookX;
                    blockY[0] = hookY;
                    blockTargetTilt[0] = 0;
                    blockTilt[0] = 0;
                    floorIndex = Math.max(0, Math.min(4, blockCount - 1));
                }
            }
            if (roundFinishedQuick) {
                buildResultText(true);
                phase = PHASE_RESULT;
            }
            if (progress[2] == 0 && progress[1] != 0 && perfectTipActiveQuick) {
                House.showPrompt(Resources.getString(35), null, null);
                screenState = 8;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new StringBuffer().append("Exception in loadTowerQuickMode(), e:").append(e.getMessage()).toString());
        }
    }

    protected final void updateBackground(int dt) {
        int i2;
        int i3 = 0;
        if (menuClouds == null) {
            menuClouds = new int[40];
            for (i2 = 0; i2 < menuClouds.length / 5; i2++) {
                menuClouds[(i2 * 5) + 1] = viewHeightUnits;
            }
        }
        if (menuCitizens == null) {
            menuCitizens = new int[18];
            for (i2 = 0; i2 < menuCitizens.length / 6; i2++) {
                menuCitizens[(i2 * 6) + 1] = viewHeightUnits;
            }
        }
        if (flyerFrames[3] == null) {
            flyerFrames[3] = Resources.getImage(45);
        }
        if (menuCloudInit == null) {
            menuCloudInit = new int[20];
            for (i2 = 0; i2 < menuCloudInit.length / 5; i2++) {
                menuCloudInit[(i2 * 5) + 1] = viewHeightUnits;
            }
        }
        if (dt > 150) {
            dt = 150;
        }
        backgroundTimer += dt;
        if (backgroundTimer >= 25) {
            int i4;
            int i5;
            int[] iArr;
            int i6;
            int[] iArr2;
            int i7;
            int i8 = backgroundTimer;
            backgroundTimer = 0;
            loadingScroll += i8 >> 1;
            for (i2 = 0; i2 < menuClouds.length / 5; i2++) {
                i4 = menuClouds[(i2 * 5) + 2];
                i5 = menuClouds[(i2 * 5) + 3];
                iArr = menuClouds;
                i6 = (i2 * 5) + 0;
                iArr[i6] = ((i4 * i8) >> 6) + iArr[i6];
                iArr2 = menuClouds;
                i7 = (i2 * 5) + 1;
                iArr2[i7] = ((i5 * i8) >> 6) + iArr2[i7];
                if (menuClouds[(i2 * 5) + 1] >= viewHeightUnits) {
                    menuClouds[(i2 * 5) + 0] = ((-((cloudLargeSprite.getWidth() >> 1) << 8)) / 32) + House.random(viewWidthUnits);
                    menuClouds[(i2 * 5) + 1] = ((-(cloudLargeSprite.getHeight() << 8)) / 32) - House.random(viewHeightUnits);
                    i4 = House.random(2);
                    menuClouds[(i2 * 5) + 4] = i4;
                    menuClouds[(i2 * 5) + 2] = (i4 * 0) + 0;
                    menuClouds[(i2 * 5) + 3] = (i4 * 3) + 6;
                }
            }
            menuCitizenTimer -= i8;
            if (menuCitizenTimer < 0) {
                menuCitizenTimer = 300;
                for (i2 = 0; i2 < menuCitizens.length / 6; i2++) {
                    iArr2 = menuCitizens;
                    i5 = (i2 * 6) + 2;
                    iArr = menuCitizens;
                    i6 = (i2 * 6) + 2;
                    int i9 = iArr[i6] + 1;
                    iArr[i6] = i9;
                    iArr2[i5] = i9 % 8;
                }
            }
            for (i2 = 0; i2 < menuCitizens.length / 6; i2++) {
                i4 = menuCitizens[(i2 * 6) + 3];
                i5 = menuCitizens[(i2 * 6) + 4];
                iArr = menuCitizens;
                i6 = (i2 * 6) + 0;
                iArr[i6] = ((i4 * i8) >> 6) + iArr[i6];
                iArr2 = menuCitizens;
                i7 = (i2 * 6) + 1;
                iArr2[i7] = ((i5 * i8) >> 6) + iArr2[i7];
                if (menuCitizens[(i2 * 6) + 1] >= viewHeightUnits) {
                    menuCitizens[(i2 * 6) + 0] = House.random(viewWidthUnits);
                    menuCitizens[(i2 * 6) + 1] = -224 - House.random(viewHeightUnits);
                    menuCitizens[(i2 * 6) + 2] = House.random(8);
                    menuCitizens[(i2 * 6) + 3] = House.random(16) - 8;
                    menuCitizens[(i2 * 6) + 4] = House.random(10) + 10;
                    menuCitizens[(i2 * 6) + 5] = House.random(2);
                }
            }
            while (i3 < menuCloudInit.length / 5) {
                i2 = menuCloudInit[(i3 * 5) + 2];
                i4 = menuCloudInit[(i3 * 5) + 3];
                int[] iArr3 = menuCloudInit;
                i7 = (i3 * 5) + 0;
                iArr3[i7] = ((i2 * i8) >> 6) + iArr3[i7];
                int[] iArr4 = menuCloudInit;
                i5 = (i3 * 5) + 1;
                iArr4[i5] = ((i4 * i8) >> 6) + iArr4[i5];
                if (menuCloudInit[(i3 * 5) + 1] >= viewHeightUnits) {
                    menuCloudInit[(i3 * 5) + 0] = (((-((flyerFrames[3].getWidth() >> 1) << 8)) / 32) + House.random(viewWidthUnits)) - viewWidthUnits;
                    menuCloudInit[(i3 * 5) + 1] = ((-(flyerFrames[3].getHeight() << 8)) / 32) - House.random(viewHeightUnits);
                    i2 = House.random(2);
                    menuCloudInit[(i3 * 5) + 2] = (i2 * 6) + 6;
                    menuCloudInit[(i3 * 5) + 3] = (i2 * 3) + 6;
                }
                i3++;
            }
            Storage.dirty = true;
        }
    }

    protected final void loadTowerInfoCityMode() {
        int i = 0;
        System.out.println("loadTowerInfoCityMode()");
        try {
            int i2;
            int i3;
            DataInputStream dis = Storage.openRead("cityModeRS");
            gameMode = dis.readInt();
            screenState = dis.readInt();
            phase = dis.readInt();
            gameTime = dis.readInt();
            centerX = dis.readInt();
            centerY = dis.readInt();
            camX = dis.readInt();
            camY = dis.readInt();
            camZ = dis.readInt();
            camTargetY = dis.readInt();
            camEaseStart = dis.readInt();
            pivotX = dis.readInt();
            pivotY = dis.readInt();
            hookX = dis.readInt();
            hookY = dis.readInt();
            swingAngle = dis.readInt();
            craneTime = dis.readInt();
            craneYOffset = dis.readInt();
            ropeLength = dis.readInt();
            swingPeriod = dis.readInt();
            prevHookX = dis.readInt();
            prevHookY = dis.readInt();
            swingAmplitudeX = dis.readInt();
            swingAmplitudeY = dis.readInt();
            skyStage = dis.readInt();
            fenceHeight = dis.readInt();
            fenceContainerWidth = dis.readInt();
            fenceChainWidth = dis.readInt();
            fenceWoodWidth = dis.readInt();
            skyStageLength = dis.readInt();
            blockCount = dis.readInt();
            blockGoal = dis.readInt();
            towerType = dis.readInt();
            foundationOffset = dis.readInt();
            unusedValue = dis.readInt();
            scrolledOffsetSum = dis.readInt();
            offsetSum = dis.readInt();
            topFloorSlot = dis.readInt();
            swayAngle = dis.readInt();
            swayAmplitude = dis.readInt();
            swayPhase = dis.readInt();
            for (i2 = 0; i2 < floorX.length; i2++) {
                floorX[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < floorY.length; i2++) {
                floorY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < floorSway.length; i2++) {
                floorSway[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < floorAngle.length; i2++) {
                floorAngle[i2] = dis.readInt();
            }
            bonusEligible = dis.readBoolean();
            floorIndex = dis.readInt();
            landingQuality = dis.readInt();
            for (i2 = 0; i2 < blockX.length; i2++) {
                blockX[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockY.length; i2++) {
                blockY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockState.length; i2++) {
                blockState[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockTilt.length; i2++) {
                blockTilt[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockAngle.length; i2++) {
                blockAngle[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockVelX.length; i2++) {
                blockVelX[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockVelY.length; i2++) {
                blockVelY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockStartY.length; i2++) {
                blockStartY[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < blockTargetAngle.length; i2++) {
                blockTargetAngle[i2] = dis.readInt();
            }
            for (i2 = 0; i2 < landingOffsets.length; i2++) {
                landingOffsets[i2] = dis.readByte();
            }
            for (i2 = 0; i2 < blockTargetTilt.length; i2++) {
                blockTargetTilt[i2] = dis.readInt();
            }
            for (i3 = 0; i3 < 8; i3++) {
                for (i2 = 0; i2 < 8; i2++) {
                    citizens[i3][i2] = dis.readInt();
                }
            }
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 5; i2++) {
                    lostBlocks[i3][i2] = dis.readInt();
                }
            }
            hudX = dis.readInt();
            hudHeight = dis.readInt();
            score = dis.readInt();
            lives = dis.readInt();
            lastLossAmount = dis.readInt();
            lastLossTime = dis.readInt();
            lastPerfectTime = dis.readInt();
            comboTimer = dis.readInt();
            comboBonus = dis.readInt();
            comboCount = dis.readInt();
            lastLandTime = dis.readInt();
            skylineStart = dis.readInt();
            for (i3 = 0; i3 < 12; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    clouds[i3][i2] = dis.readInt();
                }
            }
            for (i3 = 0; i3 < 9; i3++) {
                for (i2 = 0; i2 < 6; i2++) {
                    flyers[i3][i2] = dis.readInt();
                }
            }
            for (i2 = 0; i2 < progress.length; i2++) {
                progress[i2] = dis.readInt();
            }
            roundFinishedCity = dis.readBoolean();
            maxCombo = dis.readInt();
            House.perfectTipActiveCity = dis.readBoolean();
            newRecord = dis.readBoolean();
            raining = dis.readBoolean();
            snowing = dis.readBoolean();
            reservedFlagA = dis.readBoolean();
            rainTriggered = dis.readBoolean();
            snowTriggered = dis.readBoolean();
            lightingEnabled = dis.readBoolean();
            reservedFlagB = dis.readBoolean();
            lightLevel = dis.readInt();
            lightBefore = dis.readInt();
            lightTarget = dis.readInt();
            lightFadeDuration = dis.readInt();
            flashLevel = dis.readInt();
            flashPeak = dis.readInt();
            flashStart = dis.readInt();
            flashTimer = dis.readInt();
            flashDuration = dis.readInt();
            reservedStateFlag = dis.readInt();
            weatherPhase = dis.readInt();
            phaseDuration = dis.readInt();
            rampUpDuration = dis.readInt();
            peakDuration = dis.readInt();
            rampDownDuration = dis.readInt();
            weatherTimer = dis.readInt();
            weatherType = dis.readInt();
            reservedValue = dis.readInt();
            for (i3 = 0; i3 < 2; i3++) {
                for (i2 = 0; i2 < 7; i2++) {
                    weatherEmitters[i3][i2] = dis.readInt();
                }
            }
            while (i < 2) {
                int[] iArr = weatherEmitters[i];
                iArr[0] = weatherEmitters[i][0];
                iArr[1] = weatherEmitters[i][1];
                iArr[2] = weatherEmitters[i][2];
                iArr[3] = weatherEmitters[i][3];
                iArr[4] = weatherEmitters[i][4];
                iArr[5] = weatherEmitters[i][5];
                iArr[6] = weatherEmitters[i][6];
                i++;
            }
            lastHitTime = dis.readInt();
            Storage.close();
            if (blockState[0] == BLOCK_LANDED) {
                ropeLength = 1664;
                if (phase != PHASE_ROUND_OVER) {
                    blockState[0] = BLOCK_HANGING;
                    blockX[0] = hookX;
                    blockY[0] = hookY;
                    blockTargetTilt[0] = 0;
                    blockTilt[0] = 0;
                    floorIndex = Math.max(0, Math.min(4, blockCount - 1));
                }
            }
            if (roundFinishedCity) {
                if (landingQuality == 0) {
                    House.showPrompt(Resources.getString(56), null, null);
                    screenState = 5;
                } else if (landingQuality == 2) {
                    House.showPrompt(Resources.getString(57), null, null);
                    screenState = 5;
                } else {
                    buildResultText(false);
                }
                phase = PHASE_RESULT;
            }
            if (progress[2] == 0 && progress[0] != 0 && House.perfectTipActiveCity) {
                House.showPrompt(Resources.getString(35), null, null);
                screenState = 8;
            }
        } catch (Exception e) {
        }
    }

    protected final void onLoadBegin() {
        this.splashImage = Resources.getImage(-1);
        this.titleLogoImage = Resources.getImage(11);
    }

    protected final void onLoadEnd() {
        this.splashImage = null;
        this.titleLogoImage = null;
    }

    protected final void onMenuTick() {
        boolean z;
        House house;
        boolean z2 = true;
        boolean z3 = this.soundEnabled;
        if (Storage.getSetting(3) == 1) {
            z = true;
            house = this;
        } else {
            z = false;
            house = this;
        }
        house.soundEnabled = z;
        this.sound.setEnabled(this.soundEnabled);
        if (!z3 && this.soundEnabled) {
            this.sound.play(-2147483568, -1);
        }
        if (this.vibra != null) {
            if (Storage.getSetting(0) != 1) {
                z2 = false;
            }
            vibrationOn = z2;
            this.vibra.setEnabled(vibrationOn);
        }
    }

    public final void onEnter() {
        int i = 2;
        int i2 = 1;
        this.softKeyCommand = new Command("", 2, 1);
        promptMarginX = screenWidth / 44;
        promptMarginY = Math.max(promptMarginX, ((screenHeight - ((gameFont.getHeight() + 2) * 5)) - 48) >> 1);
        promptPaddingY = 24;
        promptPaddingX = screenWidth / 14;
        loadCancelled = false;
        this.pendingModeChange = 0;
        this.canvas.setCommand(this.softKeyCommand, this.softKeyArrowImage);
        if (Storage.getSetting(12) == 3) {
            if (!hasCitySave) {
                hasCitySave = true;
            }
            gameMode = GAME_MODE_CITY_TOWER;
            CityMode.init();
            CityMode.loadState();
            CityMode.towerGameActive = false;
            CityMode.clearInputState();
            nextScreenState = 2;
            screenState = 7;
        } else if (Storage.getSetting(12) == 4) {
            if (!hasQuickSave) {
                hasQuickSave = true;
            }
            gameMode = GAME_MODE_QUICK;
            towerType = 4;
            blockGoal = goalByTowerType[towerType - 1];
            House.initArrays();
            loadTowerQuickMode();
            House.spawnWeatherParticles();
            nextScreenState = 1;
            screenState = 7;
        } else {
            gameMode = Storage.getSetting(12);
            if (gameMode == GAME_MODE_CITY_TOWER) {
                cityInitPending = true;
                if (hasCitySave) {
                    int i3;
                    if (CityMode.isTowerGameActive()) {
                        CityMode.towerGameActive = false;
                    }
                    if (cityInitialized) {
                        cityInitialized = false;
                    }
                    CityMode.init();
                    if (bonusEligible) {
                        bonusEligible = false;
                    }
                    if (CityMode.unlockedBonusTier > 0) {
                        CityMode.unlockedBonusTier = 0;
                    }
                    if (CityMode.level > 0) {
                        CityMode.level = 0;
                    }
                    if (CityMode.population > 0) {
                        CityMode.population = 0;
                    }
                    if (CityMode.grid == null || CityMode.tutorialShown == null || CityMode.cellLevels == null) {
                        CityMode.grid = new int[75];
                        CityMode.tutorialShown = new boolean[46];
                        CityMode.cellLevels = new int[25];
                    }
                    for (i3 = 0; i3 < CityMode.grid.length; i3++) {
                        CityMode.grid[i3] = 0;
                    }
                    for (i3 = 0; i3 < CityMode.tutorialShown.length; i3++) {
                        CityMode.tutorialShown[i3] = false;
                    }
                    CityMode.phase = 0;
                    CityMode.cursorCol = 2;
                    CityMode.cursorRow = 2;
                    CityMode.population = 0;
                    CityMode.populationFlashCount = 0;
                    CityMode.unlockedSpecialTier = -1;
                    CityMode.modalActive = false;
                    CityMode.needsRepaint = true;
                    CityMode.slideTimer = 0;
                    CityMode.placementInProgress = false;
                    CityMode.populationFlashTimer = 0;
                    CityMode.populationPending = 0;
                    CityMode.highScorePending = false;
                    CityMode.launchDelay = -1;
                    CityMode.placementResultTimer = -1;
                    CityMode.updateUnlocks();
                    CityMode.recalculateSynergies();
                    CityMode.selectedBuilding = 0;
                    if (!CityMode.tutorialShown[0]) {
                        House.showPrompt(Resources.getString(36), null, null);
                        CityMode.tutorialShown[0] = true;
                        CityMode.modalActive = true;
                    }
                }
                roundFinishedCity = false;
                screenState = 7;
                i = 2;
            } else {
                towerType = 4;
                blockGoal = goalByTowerType[towerType - 1];
                House.initArrays();
                roundFinishedQuick = false;
                screenState = 7;
                i = 1;
            }
            nextScreenState = i;
            if (gameMode != GAME_MODE_QUICK) {
                i2 = 3;
            }
            markSavedGame(i2);
        }
    }

    public final void onModeChange() {
        switch (this.pendingModeChange) {
            case 1:
                House.unloadModels();
                if (cityInitialized) {
                    CityMode.unloadAssets();
                }
                skyStage = -1;
                if (this.softKeyCommand != null) {
                    this.canvas.removeCommand(this.softKeyCommand);
                }
                this.menu.gotoMenu(1);
                break;
            case 2:
                PrintStream printStream;
                String str;
                House.unloadModels();
                if (cityInitialized) {
                    CityMode.unloadAssets();
                }
                skyStage = -1;
                if (this.softKeyCommand != null) {
                    this.canvas.removeCommand(this.softKeyCommand);
                }
                screenState = 7;
                CityMode.towerGameActive = false;
                if (gameMode != GAME_MODE_QUICK) {
                    if (gameMode == GAME_MODE_CITY_TOWER) {
                        markSavedGame(3);
                        hasCitySave = true;
                        CityMode.saveState();
                        House.saveTowerCityMode();
                        printStream = System.out;
                        str = "saveToweInfoCityMode() in MODE_CHANGE_BACK";
                    }
                    this.menu.gotoMenu(2);
                    break;
                }
                markSavedGame(1);
                hasQuickSave = true;
                House.saveTowerQuickMode();
                printStream = System.out;
                str = "saveToweInfoQuickMode() in MODE_CHANGE_BACK";
                printStream.println(str);
                this.menu.gotoMenu(2);
                break;
            case 3:
                Storage.setScreenId(2);
                gameMode = Storage.getScreenId();
                break;
        }
        this.pendingModeChange = 0;
    }

    public final int[][] getHighScoreLayout() {
        return HIGH_SCORE_LAYOUT;
    }

    public static int getMode() {
        return screenState;
    }

    public static void dropBlock() {
        if (screenState == 2) {
            CityMode.handleClick(0, 0);
        } else if (screenState == 4 || screenState == 5 || screenState == 6 || screenState == 8) {
            House.handleModalAction(0);
        } else if (screenState != 7) {
            inputSelect = true;
        }
    }
}
