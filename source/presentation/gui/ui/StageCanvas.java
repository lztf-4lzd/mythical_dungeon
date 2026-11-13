package source.presentation.gui.ui;

import source.presentation.gui.asset.AssetRegistry;
import source.presentation.gui.animation.AnimationManager;
import source.presentation.gui.animation.AnimationStage;
import source.presentation.gui.animation.PulseAnimation;
import source.presentation.gui.animation.FloatingText;
import source.presentation.gui.animation.FrameAnimation;
import source.presentation.gui.animation.FlashAnimation;
import source.presentation.gui.animation.ImpactAnimation;
import source.presentation.gui.animation.LungeAnimation;
import source.presentation.gui.animation.ProjectileAnimation;
import source.presentation.gui.animation.ShakeAnimation;
import source.application.flow.MotionClassifier;
import source.application.view.AnimationEvent;
import source.application.view.CombatantView;
import source.application.view.MotionType;
import source.domain.entity.elemental.Elemental;
import source.domain.entity.skill.Skill;
import source.application.combat.TurnReport;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Khung vẽ sàn đấu pixel art 2D hiển thị anh hùng và quái vật đối đầu trên nền hầm ngục,
 * điều phối hoạt ảnh đòn đánh, đạn bay, hạt va chạm và chữ sát thương nổi mà không đọc đĩa khi vẽ.
 * Người phụ trách: Thành viên 4 (UI Pixel & Custom Components)
 */
@SuppressWarnings("this-escape")
public final class StageCanvas extends JPanel implements AnimationStage {
    private static final long serialVersionUID = 1L;

    /** Chiều cao sàn đấu mặc định (pixel). */
    private static final int DEFAULT_STAGE_HEIGHT = 180;
    /** Chiều cao sàn đấu tối thiểu (pixel). */
    private static final int MIN_STAGE_HEIGHT = 140;
    /** Kích thước chuẩn của mỗi khung sprite (pixel). */
    private static final int SPRITE_SIZE = 72;

    /** Hệ số phóng đại hiển thị của anh hùng. */
    private static final double DISPLAY_SCALE_HERO = 1.65;
    /** Hệ số phóng đại hiển thị của quái thường. */
    private static final double DISPLAY_SCALE_NORMAL = 1.65;
    /** Hệ số phóng đại hiển thị của quái trùm (Boss). */
    private static final double DISPLAY_SCALE_BOSS = 1.90;

    // =========================================================================
    // Hằng số cấu hình thời lượng hoạt ảnh chiến đấu
    // =========================================================================

    /** Thời lượng bay của đạn tầm xa (mili-giây). */
    private static final long DEFAULT_PROJECTILE_DURATION_MS = 338L;

    /** Thời lượng lao tới cận chiến (mili-giây). */
    private static final long DEFAULT_MELEE_LUNGE_DURATION_MS = 280L;

    /** Độ trễ tính tới điểm chạm đòn cận chiến ở đỉnh lao tới (mili-giây). */
    private static final long DEFAULT_MELEE_IMPACT_DELAY_MS = 140L;

    /** Thời lượng vòng xung lượng tự cường hóa / hồi máu (mili-giây). */
    private static final long DEFAULT_BUFF_PULSE_DURATION_MS = 330L;

    /** Độ trễ điểm nổ hiệu ứng tự cường hóa / hồi máu (mili-giây). */
    private static final long DEFAULT_BUFF_IMPACT_DELAY_MS = 190L;

    /** Thời lượng vòng tụ phép trước khi phóng chưởng (mili-giây). */
    private static final long DEFAULT_CASTING_PULSE_DURATION_MS = 200L;

    /** Độ trễ trước khi phóng đạn phép trong chuyển động niệm chú (mili-giây). */
    private static final long DEFAULT_CASTING_LAUNCH_DELAY_MS = 96L;

    /** Thời lượng bay của đạn phép sau khi niệm chú (mili-giây). */
    private static final long DEFAULT_CASTING_PROJECTILE_DURATION_MS = 104L;

    /** Thời lượng chớp trắng khi mục tiêu trúng đòn (mili-giây). */
    private static final long DEFAULT_HIT_FLASH_DURATION_MS = 190L;

    /** Thời lượng chớp trắng khi mục tiêu chịu sát thương theo lượt DoT (mili-giây). */
    private static final long DEFAULT_DOT_FLASH_DURATION_MS = 140L;

    /** Thời lượng rung màn hình khi nổ chí mạng hoặc đòn đánh mạnh (mili-giây). */
    private static final long DEFAULT_SCREEN_SHAKE_DURATION_MS = 260L;

    /** Thời lượng hiển thị chữ sát thương / hồi phục nổi (mili-giây). */
    private static final long DEFAULT_FLOATING_TEXT_DURATION_MS = 950L;

    /** Độ trễ chờ hoàn tất nhịp rút máu DoT đầu lượt (mili-giây). */
    private static final long DEFAULT_DOT_SETTLE_DELAY_MS = 235L;

    // =========================================================================
    // Tiện ích tính khung bao chiến binh & tọa độ đường đạn
    // =========================================================================

    /**
     * Trả về hình chữ nhật bao quanh sprite anh hùng trên sàn đấu hiện tại.
     */
    private Rectangle getHeroSpriteBounds() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return StageGeometry.calculateHeroSpriteBounds(
        // 2. getWidth(), getHeight(), heroOffsetX, SPRITE_SIZE);
        return null;
    }

    /**
     * Trả về hình chữ nhật bao quanh sprite quái vật trên sàn đấu hiện tại.
     */
    private Rectangle getMonsterSpriteBounds() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return StageGeometry.calculateMonsterSpriteBounds(
        // 2. getWidth(), getHeight(), monsterOffsetX, SPRITE_SIZE);
        return null;
    }

    /**
     * Tính tọa độ điểm xuất phát của đạn (x, y) dựa trên khung bao và hệ nghề của người tấn công.
     */
    private Point calculateProjectileLaunchPoint(boolean isHeroAttacker, String attackerClass) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return StageGeometry.calculateProjectileLaunchPoint(
        // 2. getHeroSpriteBounds(), getMonsterSpriteBounds(), isHeroAttacker, attackerClass);
        return null;
    }

    /**
     * Tính tọa độ điểm va chạm mục tiêu (x, y) tại tâm thân của chiến binh bị tấn công.
     */
    private Point calculateProjectileTargetPoint(boolean isHeroAttacker) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return StageGeometry.calculateProjectileTargetPoint(
        // 2. getHeroSpriteBounds(), getMonsterSpriteBounds(), isHeroAttacker);
        return null;
    }

    /**
     * Kiểm tra hành động phóng đạn có sử dụng kiểu mũi tên bay vòng cung ({@code ARROW}) hay không,
     * hỗ trợ truyền tên kỹ năng dự phòng.
     */
    private static boolean isArrowProjectile(String attackerClass, source.domain.entity.skill.Skill skill, String fallbackSkillName) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (attackerClass != null)
        // 2. String cls = attackerClass.trim().toLowerCase(java.util.Locale.ROOT);
        // 3. if (cls.equals("archer") || cls.equals("skeleton_archer")
        // 4. || cls.equals("demon_b") || cls.equals("soldier")
        // 5. || cls.contains("skeleton archer") || cls.contains("demon b")
        // 6. || cls.contains("soldier"))
        return false;
    }

    /**
     * Phân giải kiểu hình ảnh đạn bay từ lớp nhân vật, kỹ năng và tên kỹ năng dự phòng.
     */
    private static ProjectileAnimation.ProjectileStyle resolveProjectileStyle(String attackerClass, source.domain.entity.skill.Skill skill, String fallbackSkillName) {
        return isArrowProjectile(attackerClass, skill, fallbackSkillName)
                ? ProjectileAnimation.ProjectileStyle.ARROW
                : ProjectileAnimation.ProjectileStyle.FIREBALL;
    }

    /** Màu đỉnh phông nền mặc định của sàn đấu. */
    private static final Color STAGE_BG_TOP = BackdropRenderer.STAGE_BG_TOP;

    /** Dữ liệu hiển thị hiện tại của anh hùng. */
    private transient CombatantView hero;
    /** Dữ liệu hiển thị hiện tại của quái vật. */
    private transient CombatantView monster;
    /** Số thứ tự tầng hầm ngục hiện tại. */
    private int floorNumber = 1;
    /** Cờ báo đang ở lượt của người chơi. */
    private boolean isPlayerTurn = true;
    /** Cờ báo trận đấu đã kết thúc. */
    private boolean isGameOver = false;
    /** Loại hầm ngục phe phái đang chọn. */
    private source.application.dungeon.FloorCatalog.DungeonType dungeonType;

    // Bộ đệm ảnh sprite giúp paintComponent không bao giờ đọc đĩa trực tiếp
    private transient BufferedImage heroSprite;
    private transient BufferedImage monsterSprite;
    private transient BufferedImage backgroundSprite;
    private transient BufferedImage heroFlashSprite;
    private transient BufferedImage monsterFlashSprite;
    private String cachedHeroKey = "";
    private String cachedMonsterKey = "";

    // Hoạt ảnh khung hình (Idle, Attack, Hurt, Death)
    private transient FrameAnimation heroIdleAnimation;
    private transient FrameAnimation monsterIdleAnimation;
    private transient FrameAnimation heroAttackAnimation;
    private transient FrameAnimation heroHurtAnimation;
    private transient FrameAnimation heroDeathAnimation;
    private transient FrameAnimation monsterAttackAnimation;
    private transient FrameAnimation monsterHurtAnimation;
    private transient FrameAnimation monsterDeathAnimation;

    // Độ lệch tọa độ động và độ mờ chớp sáng khi diễn hoạt
    private int shakeOffsetX = 0;
    private int shakeOffsetY = 0;
    private int heroOffsetX = 0;
    private int monsterOffsetX = 0;
    private float heroFlashAlpha = 0.0f;
    private float monsterFlashAlpha = 0.0f;

    /** Danh sách các chữ sát thương/hồi phục nổi đang hoạt động. */
    private final transient List<FloatingText> activeFloatingTexts = new CopyOnWriteArrayList<>();
    /** Danh sách các đường đạn đang bay. */
    private final transient List<ProjectileAnimation> activeProjectiles = new CopyOnWriteArrayList<>();
    /** Danh sách các vòng xung lượng niệm chú đang phát sáng. */
    private final transient List<PulseAnimation> activeCastingPulses = new CopyOnWriteArrayList<>();
    /** Danh sách các vụ nổ hạt va chạm đang hiển thị. */
    private final transient List<ImpactAnimation> activeImpactEffects = new CopyOnWriteArrayList<>();

    /** Bộ quản lý vòng lặp hoạt ảnh của sàn đấu. */
    private final transient AnimationManager animationManager = new AnimationManager(this);
    /** Bộ vẽ môi trường phông nền và sàn đá. */
    private final transient BackdropRenderer environmentRenderer = new BackdropRenderer();
    /** Bộ vẽ sprite chiến binh, thanh HUD mini và bảng tên. */
    private final transient CombatantRenderer combatantRenderer = new CombatantRenderer();

    /**
     * Khởi tạo khung vẽ sàn đấu với kích thước và đường viền mặc định.
     */
    public StageCanvas() {
        // TODO: Hướng dẫn hiện thực:
        // 1. setPreferredSize(new Dimension(800, DEFAULT_STAGE_HEIGHT));
        // 2. setMinimumSize(new Dimension(400, MIN_STAGE_HEIGHT));
        // 3. setBackground(STAGE_BG_TOP);
        // 4. setBorder(BorderFactory.createCompoundBorder(
        // 5. BorderFactory.createLineBorder(Theme.BORDER_NORMAL, 1, true),
        // 6. BorderFactory.createEmptyBorder(2, 2, 2, 2)));
    }

    /**
     * Cập nhật trạng thái chiến binh và lượt đấu trên sàn, đồng bộ lại bộ đệm sprite và vẽ lại.
     */
    public void updateState(CombatantView hero, CombatantView monster, int floorNumber, boolean isPlayerTurn, boolean isGameOver) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.hero = hero;
        // 2. this.monster = monster;
        // 3. this.floorNumber = Math.max(1, floorNumber);
        // 4. this.isPlayerTurn = isPlayerTurn;
        // 5. this.isGameOver = isGameOver;
        // 6. if (environmentRenderer != null && this.dungeonType == null)
    }

    /**
     * Thiết lập loại hầm ngục phe phái để đổi chủ đề phông nền sàn đấu.
     */
    public void setDungeonType(source.application.dungeon.FloorCatalog.DungeonType dungeonType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.dungeonType = dungeonType;
        // 2. if (environmentRenderer != null)
        // 3. environmentRenderer.setThemeFromDungeon(dungeonType);
        // 4. repaint();
    }

    /** Đồng bộ ảnh tĩnh và hoạt ảnh khung hình khi chiến binh thay đổi hoặc vừa gục ngã. */
    private void syncSprites() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (backgroundSprite == null)
        // 2. backgroundSprite = AssetRegistry.getImage("assets/backgrounds/dungeon_stage.png");
        // 3. String heroKey = (hero != null) ? (hero.getClassName() + ":" + hero.getName()) : "none";
        // 4. if (!heroKey.equals(cachedHeroKey) || heroSprite == null)
        // 5. cachedHeroKey = heroKey;
        // 6. String path = SpriteResolver.resolveHeroSpritePath(hero);
    }

    /** Tạo ảnh bóng trắng từ sprite nguồn để làm hiệu ứng chớp sáng khi trúng đòn. */
    private static BufferedImage createTintedFlashImage(BufferedImage source) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return CombatantRenderer.createTintedFlashImage(source);
        return null;
    }

    /** Tải ảnh sprite từ bộ đệm hoặc sinh hình đại diện theo màu chủ đề nếu thiếu tệp. */
    private BufferedImage loadOrFallbackSprite(String path, boolean isHero, CombatantView combatant) {
        // TODO: Hướng dẫn hiện thực:
        // 1. BufferedImage img = AssetRegistry.getImage(path);
        // 2. if (AssetRegistry.isCached(path))
        // 3. return img;
        // 4. Tạo ảnh đại diện dự phòng với màu chủ đề và chữ viết tắt 3 ký tự
        // 5. Color bg;
        // 6. String label;
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO: Hướng dẫn hiện thực:
        // 1. super.paintComponent(g);
        // 2. int w = getWidth();
        // 3. int h = getHeight();
        // 4. if (w <= 0 || h <= 0) return;
        // 5. Graphics2D g2d = (Graphics2D) g.create();
        // 6. try
    }

    /** Vẽ phông nền sàn đấu qua {@link BackdropRenderer}. */
    private void renderBackground(Graphics2D g2d, int w, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. environmentRenderer.renderBackground(g2d, w, groundY, backgroundSprite);
    }

    /** Vẽ mặt sàn đá qua {@link BackdropRenderer}. */
    private void renderFloor(Graphics2D g2d, int w, int h, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. environmentRenderer.renderFloor(g2d, w, h, groundY);
    }

    /** Vẽ chỉ báo lượt, thanh HUD trên đầu và bảng tên qua {@link CombatantRenderer}. */
    private void renderTurnIndicatorsAndBadges(Graphics2D g2d, int heroCenterX, int monsterCenterX,
                                               int heroY, int monsterY, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. combatantRenderer.renderTurnIndicatorsAndBadges(g2d, hero, monster, isGameOver, isPlayerTurn, heroCenterX, monsterCenterX, heroY, monsterY, groundY);
    }

    /** Vẽ và dọn dẹp các vòng xung lượng niệm chú đã kết thúc. */
    private void renderCastingPulses(Graphics2D g2d, int heroCx, int monsterCx, int cy) {
        // TODO: Hướng dẫn hiện thực:
        // 1. for (PulseAnimation pulse : activeCastingPulses)
        // 2. if (!pulse.isFinished())
        // 3. int cx = pulse.isAttackerHero() ? heroCx : monsterCx;
        // 4. pulse.render(g2d, cx, cy);
        // 5. activeCastingPulses.removeIf(PulseAnimation::isFinished);
    }

    /** Vẽ và dọn dẹp các đường đạn đã bay tới đích. */
    private void renderProjectiles(Graphics2D g2d) {
        // TODO: Hướng dẫn hiện thực:
        // 1. for (ProjectileAnimation proj : activeProjectiles)
        // 2. if (!proj.isFinished())
        // 3. proj.render(g2d);
        // 4. activeProjectiles.removeIf(ProjectileAnimation::isFinished);
    }

    /** Vẽ và dọn dẹp các hiệu ứng nổ va chạm đã kết thúc. */
    private void renderImpactEffects(Graphics2D g2d) {
        // TODO: Hướng dẫn hiện thực:
        // 1. for (ImpactAnimation effect : activeImpactEffects)
        // 2. if (!effect.isFinished())
        // 3. effect.render(g2d);
        // 4. activeImpactEffects.removeIf(ImpactAnimation::isFinished);
    }

    /** Vẽ và dọn dẹp các chữ nổi đã hết thời gian hiển thị. */
    private void renderFloatingTexts(Graphics2D g2d) {
        // TODO: Hướng dẫn hiện thực:
        // 1. for (FloatingText ft : activeFloatingTexts)
        // 2. if (!ft.isFinished())
        // 3. ft.render(g2d);
        // 4. activeFloatingTexts.removeIf(FloatingText::isFinished);
    }

    /** Sinh hiệu ứng nổ va chạm tại tọa độ cho trước với màu mặc định của loại va chạm. */
    private void spawnImpactEffect(int x, int y, ImpactAnimation.ImpactType type) {
        // TODO: Hướng dẫn hiện thực:
        // 1. spawnImpactEffect(x, y, type, null);
    }

    /** Sinh hiệu ứng nổ va chạm tại tọa độ cho trước với màu tùy chỉnh. */
    private void spawnImpactEffect(int x, int y, ImpactAnimation.ImpactType type, Color customColor) {
        // TODO: Hướng dẫn hiện thực:
        // 1. ImpactAnimation effect = new ImpactAnimation(x, y, type, customColor);
        // 2. activeImpactEffects.add(effect);
        // 3. animationManager.addAnimation(effect);
    }

    /** Sinh hiệu ứng nổ va chạm theo nguyên tố kỹ năng và trạng thái chí mạng. */
    private void spawnImpactEffect(int x, int y, Elemental element, boolean isCrit) {
        // TODO: Hướng dẫn hiện thực:
        // 1. ImpactAnimation.ImpactType type = isCrit
        // 2. ? ImpactAnimation.ImpactType.CRITICAL
        // 3. : (element != null && element != Elemental.NORMAL ? ImpactAnimation.ImpactType.MAGIC_BURST : ImpactAnimation.ImpactType.NORMAL);
        // 4. Color color = ImpactAnimation.resolveElementColor(element);
        // 5. spawnImpactEffect(x, y, type, color);
    }

    /**
     * Thực thi chuyển động ra đòn dựa trên sự kiện hành động và báo cáo lượt.
     */
    public void playActionMotion(
            AnimationEvent actionEvent,
            TurnReport report,
            Runnable onImpact,
            Runnable onComplete) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (actionEvent == null)
        // 2. if (onImpact != null) onImpact.run();
        // 3. if (onComplete != null) onComplete.run();
        // 4. return;
        // 5. String heroName = hero != null ? hero.getName() : "";
        // 6. boolean isHeroAttacker = !heroName.isEmpty() && heroName.equalsIgnoreCase(actionEvent.getSourceName());
    }

    /**
     * Thực thi phản ứng trúng đòn cho danh sách sự kiện va chạm kèm thông tin báo cáo lượt.
     */
    public void playImpactEvents(List<AnimationEvent> impactEvents, TurnReport report) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (impactEvents == null || impactEvents.isEmpty())
        // 2. return;
        // 3. int w = getWidth() > 0 ? getWidth() : 800;
        // 4. int h = getHeight() > 0 ? getHeight() : DEFAULT_STAGE_HEIGHT;
        // 5. int groundY = Math.max(h - 30, h * 3 / 4);
        // 6. int availableHeight = groundY - 22;
    }

    /**
     * Thực thi phản ứng rút máu theo lượt (DoT) đầu lượt trên mục tiêu.
     */
    public void playDotTick(AnimationEvent dotEvent, Runnable onComplete) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (dotEvent == null)
        // 2. if (onComplete != null) onComplete.run();
        // 3. return;
        // 4. int w = getWidth() > 0 ? getWidth() : 800;
        // 5. int h = getHeight() > 0 ? getHeight() : DEFAULT_STAGE_HEIGHT;
        // 6. int groundY = Math.max(h - 30, h * 3 / 4);
    }

    /** Tìm kỹ năng tương ứng với tên người hành động trong báo cáo lượt. */
    private Skill findSkillForAction(TurnReport report, String attackerName) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (report != null && report.getActionRecords() != null)
        // 2. for (TurnReport.ActionRecord r : report.getActionRecords())
        // 3. if (r.getAttackerName() != null && r.getAttackerName().equalsIgnoreCase(attackerName))
        // 4. return r.getSkill();
        // 5. if (!report.getActionRecords().isEmpty())
        // 6. return report.getActionRecords().get(0).getSkill();
        return null;
    }

    /** Tra cứu nguyên tố của kỹ năng gây ra sự kiện hoạt ảnh. */
    private Elemental resolveEventElement(AnimationEvent event, TurnReport report) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (event != null && report != null)
        // 2. Skill skill = findSkillForAction(report, event.getSourceName());
        // 3. if (skill != null)
        // 4. return skill.getElement();
        // 5. return null;
        return null;
    }

    /** Phân giải màu vòng xung lượng niệm chú theo nguyên tố kỹ năng. */
    private Color resolvePulseColor(Elemental element) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (element == null) return Theme.SAPPHIRE;
        // 2. switch (element)
        // 3. case FIRE: return Theme.RUBY;
        // 4. case WATER: return Theme.SAPPHIRE;
        // 5. case LIGHTNING: return new Color(255, 240, 60);
        // 6. case GROUND: return Theme.EMERALD;
        return null;
    }

    /**
     * Trả về bộ quản lý hoạt ảnh đang điều phối khung vẽ này.
     */
    public AnimationManager getAnimationManager() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return animationManager;
        return null;
    }

    /** Kích hoạt hoạt ảnh khung hình tấn công của anh hùng. */
    private void triggerHeroAttack() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (hero == null) return;
        // 2. String path = SpriteResolver.resolveHeroAttackSheetPath(hero);
        // 3. List<BufferedImage> frames = AssetRegistry.loadSpriteSheet(path, SPRITE_SIZE, SPRITE_SIZE);
        // 4. if (!frames.isEmpty())
        // 5. if (heroAttackAnimation != null)
        // 6. animationManager.cancelAnimation(heroAttackAnimation);
    }

    /** Kích hoạt hoạt ảnh khung hình trúng đòn của anh hùng. */
    private void triggerHeroHurt() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (hero == null) return;
        // 2. String path = SpriteResolver.resolveHeroHurtSheetPath(hero);
        // 3. List<BufferedImage> frames = AssetRegistry.loadSpriteSheet(path, SPRITE_SIZE, SPRITE_SIZE);
        // 4. if (!frames.isEmpty())
        // 5. if (heroHurtAnimation != null)
        // 6. animationManager.cancelAnimation(heroHurtAnimation);
    }

    /** Kích hoạt hoạt ảnh khung hình tấn công của quái vật. */
    private void triggerMonsterAttack() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (monster == null) return;
        // 2. String path = SpriteResolver.resolveMonsterAttackSheetPath(monster);
        // 3. List<BufferedImage> frames = AssetRegistry.loadSpriteSheet(path, SPRITE_SIZE, SPRITE_SIZE);
        // 4. if (!frames.isEmpty())
        // 5. if (monsterAttackAnimation != null)
        // 6. animationManager.cancelAnimation(monsterAttackAnimation);
    }

    /** Kích hoạt hoạt ảnh khung hình trúng đòn của quái vật. */
    private void triggerMonsterHurt() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (monster == null) return;
        // 2. String path = SpriteResolver.resolveMonsterHurtSheetPath(monster);
        // 3. List<BufferedImage> frames = AssetRegistry.loadSpriteSheet(path, SPRITE_SIZE, SPRITE_SIZE);
        // 4. if (!frames.isEmpty())
        // 5. if (monsterHurtAnimation != null)
        // 6. animationManager.cancelAnimation(monsterHurtAnimation);
    }

    /**
     * Hủy toàn bộ hoạt ảnh đang chạy và đặt lại trạng thái dịch chuyển của sàn đấu.
     */
    public void clearAnimations() {
        // TODO: Hướng dẫn hiện thực:
        // 1. animationManager.cancelAll();
        // 2. if (heroIdleAnimation != null)
        // 3. heroIdleAnimation = null;
        // 4. if (monsterIdleAnimation != null)
        // 5. monsterIdleAnimation = null;
        // 6. if (heroAttackAnimation != null)
    }
}
