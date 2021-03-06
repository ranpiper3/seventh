/*
 * see license.txt 
 */
package seventh.client.sfx;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import leola.vm.types.LeoMap;
import leola.vm.types.LeoObject;
import paulscode.sound.ListenerData;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.SoundSystemLogger;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import seventh.client.ClientSeventhConfig;
import seventh.client.ClientTeam;
import seventh.game.net.NetSound;
import seventh.math.Vector2f;
import seventh.shared.Command;
import seventh.shared.Cons;
import seventh.shared.Console;
import seventh.shared.Scripting;
import seventh.shared.SoundType;

/**
 * @author Tony
 *
 */
public class Sounds {

//    private static ExecutorService service = Executors.newCachedThreadPool();
    public static final long uiChannel = 2004 >> 2;
    
    private static int index = 0;
    
    public static final int[] die = {index++,index++,index++};
    public static final int[] hit = {index++,index++,index++,index++,index++,index++};
    public static final int[] hitIndication = {index++};
    public static final int[] headshot = {index++};
            
//    public static final int[] normalWalk = {6,7,8,9};
    public static final int[] weaponDrop = {index++,index++};
    public static final int[] uiHover = {index++};
    public static final int[] uiSelect = {index++};
    public static final int[] gib = {index++,index++};
    
    public static final int[] bombTick = {index++};
    public static final int[] bombPlant = {index++};    
    public static final int[] bombDisarm = {index++};
    
    public static final int[] logAlert = {index++};
    public static final int[] weaponPickupSnd = {index++};
    
    public static final int[] explodeSnd = {index++,index++,index++};
    public static final int[] emptyFireSnd = {index++};
    public static final int[] ammoPickupSnd = {index++};
    public static final int[] respawnSnd = {index++};    
    public static final int[] weaponSwitch = {index++};

    public static final int[] ruffle = {index++,index++,index++};

    public static final int[] thompsonFire = {index++};
    public static final int[] thompsonReload = {index++};
    

    public static final int[] shotgunFire = {index++};
    public static final int[] shotgunPump = {index++};
    public static final int[] shotgunReload = {index++};
    
    public static final int[] springfieldFire = {index++};
    public static final int[] springfieldReload = {index++};
    public static final int[] springfieldRechamber = {index++};
    
    public static final int[] m1Fire = {index++};
    public static final int[] m1FireLast = {index++};
    public static final int[] m1Reload = {index++};
    
    public static final int[] grenadePinPulled = {index++};
    public static final int[] grenadeThrow = {index++};
    
    public static final int[] rocketFire = {index++};
    
    // TODO:
    public static final int[] kar98Fire = {index++};
    public static final int[] kar98Reload = {index++};
    public static final int[] kar98Rechamber = {index++};
    
    public static final int[] mp44Fire = {index++};
    public static final int[] mp44Reload = {index++};
    
    public static final int[] mp40Fire = {index++};
    public static final int[] mp40Reload = {index++};
    
    public static final int[] meleeSwing = {index++,index++};
    public static final int[] meleeHit = {index++,index++};
    
    public static final int[] normalWalk = {index++,index++,index++,index++};
    public static final int[] dirtWalk = {index++,index++,index++,index++};
    public static final int[] grassWalk = {index++,index++,index++,index++};
    public static final int[] metalWalk = {index++,index++,index++,index++};
    public static final int[] waterWalk = {index++,index++,index++,index++};
    public static final int[] woodWalk = {index++,index++,index++,index++};
    
    public static final int[] uiNavigate = {index++,index++};
    public static final int[] uiKeyType = {index++,index++,index++};
    
    public static final int[] pistolFire = {index++};
    public static final int[] pistolReload = {index++};
    
    
    public static final int[] impactMetal = {index++,index++,index++,index++,index++};
    public static final int[] impactWood = {index++,index++,index++,index++,index++};
    public static final int[] impactFoliage = {index++,index++,index++,index++,index++};
    public static final int[] impactGlass = {index++,index++,index++,index++,index++};
    public static final int[] impactDefault = {index++,index++,index++,index++,index++};
    
    public static final int[] riskerFire = {index++};
    public static final int[] riskerReload = {index++};
    public static final int[] riskerRechamber = {index++};
    
    public static final int[] mechForwardFootstep = {index++};
    public static final int[] mechRetractFootstep = {index++};
    public static final int[] mechTorsoMove = {index++};
    
    public static final int[] breadthLite = {index++,index++,index++};
    public static final int[] breadthHeavy = {index++,index++,index++};
    
    public static final int[] bulletZing = {index++,index++,index++};
    
    //public static final int[] tankStart = {118};
    //public static final int[] tankMove = {119};
    public static final int[] healthPackPickup = {index++};

    public static final int[] alliedSpeechAttack = {index++};
    public static final int[] alliedSpeechCoverMe = {index++};
    public static final int[] alliedSpeechFollowMe = {index++};
    public static final int[] alliedSpeechGetOutOfMyWay = {index++};
    public static final int[] alliedSpeechGetUsKilled = {index++};
    public static final int[] alliedSpeechHoldPosition = {index++};
    public static final int[] alliedSpeechIllCoverYou = {index++};
    public static final int[] alliedSpeechTakingFire = {index++};
    public static final int[] alliedSpeechYouTakeLead = {index++};

    public static final int[] axisSpeechAttack = {index++};
    public static final int[] axisSpeechCoverMe = {index++};
    public static final int[] axisSpeechFollowMe = {index++};
    public static final int[] axisSpeechGetOutOfMyWay = {index++};
    public static final int[] axisSpeechGetUsKilled = {index++};
    public static final int[] axisSpeechHoldPosition = {index++};
    public static final int[] axisSpeechIllCoverYou = {index++};
    public static final int[] axisSpeechTakingFire = {index++};
    public static final int[] axisSpeechYouTakeLead = {index++};

    public static final int[] tankOn = {index++};
    public static final int[] tankOff = {index++};
    public static final int[] tankIdle = {index++};
    public static final int[] tankShift = {index++,index++,index++};
    public static final int[] tankRevUp = {index++};
    public static final int[] tankRevDown = {index++};
    public static final int[] tankTurret = {index++};
    public static final int[] tankMove = {index++};
    public static final int[] tankFire = {index++};
    
    public static final int[] flagCaptured = {index++};
    public static final int[] flagStolen = {index++};
    public static final int[] flagReturned = {index++};
    public static final int[] enemyFlagCaptured = {index++};
    public static final int[] enemyFlagStolen = {index++};
    
    public static final int[] mg42Fire = {index++,index++};    
    public static final int[] radioStatic = {index++,index++};
    public static final int[] alliedVictory = {index++,index++,index++};
    public static final int[] axisVictory = {index++,index++,index++};
    
    public static final int[] doorOpen = {index++,index++,index++,index++};
    public static final int[] doorClose = {index++,index++};
    
    public static final int[] doorCloseBlocked = {index++,index++,index++};
    public static final int[] doorOpenBlocked = {index++};
    
    public static final int[] smokeGrenade = {index++};
    public static final int[] fire = {index++};
    public static final int[] flameThrowerShoot = {index++};
    
    public static final int[] hammer = {index++};
    
    public static final int[] bulletShell = {index++, index++,index++};
    public static final int[] shotgunShell = {index++};
    
    public static final int[][] alliedSpeeches = {
            alliedSpeechAttack,
            alliedSpeechCoverMe,
            alliedSpeechFollowMe,
            alliedSpeechGetOutOfMyWay,
            alliedSpeechGetUsKilled,
            alliedSpeechHoldPosition,
            alliedSpeechIllCoverYou,
            alliedSpeechTakingFire,
            alliedSpeechYouTakeLead,        
    };
    
    public static final int[][] axisSpeeches = {
            axisSpeechAttack,
            axisSpeechCoverMe,
            axisSpeechFollowMe,
            axisSpeechGetOutOfMyWay,
            axisSpeechGetUsKilled,
            axisSpeechHoldPosition,
            axisSpeechIllCoverYou,
            axisSpeechTakingFire,
            axisSpeechYouTakeLead,        
    };
    
    private static final Random random = new Random();
    private static Map<String, SoundBuffer> loadedSounds = new ConcurrentHashMap<>();
    private static Sound[][] channels = new Sound[64][];
    private static float volume = 0.1f;
    private static Vector2f listenerPosition = new Vector2f();
    private static ClientSeventhConfig config;
    
    
    private static Map<String, SoundConfig> soundConfigs = new HashMap<>();
    private static SoundConfig defaultSoundConfig = new SoundConfig();
    
    private static Sound[] createChannel() {
        return new Sound[] {                    
            loadSound("./assets/sfx/player/die1.wav") ,   // 0
            loadSound("./assets/sfx/player/die2.wav") ,   // 1
            loadSound("./assets/sfx/player/die3.wav") ,   // 2
            loadSound("./assets/sfx/player/hit1.wav") ,   // 3
            loadSound("./assets/sfx/player/hit2.wav") ,   // 4
            loadSound("./assets/sfx/player/hit3.wav") ,   // 5                                                
            loadSound("./assets/sfx/player/hit4.wav") ,   // 6
            loadSound("./assets/sfx/player/hit5.wav") ,   // 7
            loadSound("./assets/sfx/player/hit6.wav") ,   // 8
            
            loadSound("./assets/sfx/player/hit_indication01.wav") ,
            loadSound("./assets/sfx/player/headshot01.wav") ,     
            
            loadSound("./assets/sfx/weapon_drop01.wav") ,   // 6,
            loadSound("./assets/sfx/weapon_drop02.wav") ,   // 7,            
            loadSound("./assets/sfx/ui/element_hover.wav") ,   // 8,
            loadSound("./assets/sfx/ui/element_select.wav") ,   // 9,
            loadSound("./assets/sfx/player/body_explode1.wav"), // 10
            loadSound("./assets/sfx/player/body_explode2.wav"), // 11                        
            loadSound("./assets/sfx/bomb_tick.wav"), // 12
            loadSound("./assets/sfx/bomb_plant.wav"), // 13
            loadSound("./assets/sfx/bomb_disarm.wav"), // 14
            
            // UI stuff
            loadSound("./assets/sfx/log_alert.wav"), // 15
            loadSound("./assets/sfx/player/weapon_pickup.wav"), //16
                                                            
            // misc.
            loadSound("./assets/sfx/explosion1.wav") ,   // 17
            loadSound("./assets/sfx/explosion2.wav") ,   // 18        
            loadSound("./assets/sfx/explosion3.wav") ,   // 19        
            loadSound("./assets/sfx/empty_fire.wav") ,   // 20
            loadSound("./assets/sfx/player/ammo_pickup.wav") ,   // 21                                    
            loadSound("./assets/sfx/respawn.wav") ,   // 22
            loadSound("./assets/sfx/weapon_switch.wav") ,   // 23
            loadSound("./assets/sfx/player/ruffle1.wav"), // 24
            loadSound("./assets/sfx/player/ruffle2.wav"), // 25
            loadSound("./assets/sfx/player/ruffle3.wav"), // 26
            
            loadSound("./assets/sfx/thompson/thompson_fire.wav") ,   // 27
            loadSound("./assets/sfx/thompson/thompson_reload.wav") ,   // 28
                
            loadSound("./assets/sfx/shotgun/shotgun_fire.wav") ,        // 29
            loadSound("./assets/sfx/shotgun/shotgun_pump.wav") ,   // 30
            loadSound("./assets/sfx/shotgun/shotgun_reload.wav") ,   // 31
            
            loadSound("./assets/sfx/springfield/springfield_fire.wav") ,   // 32
            loadSound("./assets/sfx/springfield/springfield_reload.wav") ,   // 33
            loadSound("./assets/sfx/springfield/springfield_rechamber.wav") ,   // 34
        
            loadSound("./assets/sfx/m1garand/m1garand_fire.wav") ,   // 35
            loadSound("./assets/sfx/m1garand/m1garand_fire_last.wav") ,   // 36
            loadSound("./assets/sfx/m1garand/m1garand_reload.wav") ,   // 37
            

            loadSound("./assets/sfx/grenade/grenade_pinpull.wav") ,   // 38
            loadSound("./assets/sfx/grenade/grenade_throw.wav") ,   // 39
            
            loadSound("./assets/sfx/rocket/rocket_fire.wav") ,   // 40
            
            loadSound("./assets/sfx/kar98/kar98_fire.wav") ,   // 41
            loadSound("./assets/sfx/kar98/kar98_reload.wav") ,   // 42
            loadSound("./assets/sfx/kar98/kar98_rechamber.wav") ,   // 43
            
            loadSound("./assets/sfx/mp44/mp44_fire.wav") ,   // 44
            loadSound("./assets/sfx/mp44/mp44_reload.wav") ,   // 45
            
            loadSound("./assets/sfx/mp40/mp40_fire.wav") ,   // 46
            loadSound("./assets/sfx/mp40/mp40_reload.wav") ,   // 47
            
            loadSound("./assets/sfx/melee/melee_swing01.wav") ,   // 48
            loadSound("./assets/sfx/melee/melee_swing02.wav") ,   // 49
            loadSound("./assets/sfx/melee/melee_hit01.wav") ,   // 50
            loadSound("./assets/sfx/melee/melee_hit02.wav") ,   // 51
            
            // footsteps
            loadSound("./assets/sfx/player/footsteps/foot_normal01.wav") ,   // 52
            loadSound("./assets/sfx/player/footsteps/foot_normal02.wav") ,   // 53
            loadSound("./assets/sfx/player/footsteps/foot_normal03.wav") ,   // 54
            loadSound("./assets/sfx/player/footsteps/foot_normal04.wav") ,   // 55
            
            loadSound("./assets/sfx/player/footsteps/foot_dirt01.wav") ,   // 56
            loadSound("./assets/sfx/player/footsteps/foot_dirt02.wav") ,   // 57
            loadSound("./assets/sfx/player/footsteps/foot_dirt03.wav") ,   // 58
            loadSound("./assets/sfx/player/footsteps/foot_dirt04.wav") ,   // 59
            
            loadSound("./assets/sfx/player/footsteps/foot_grass01.wav") ,   // 60
            loadSound("./assets/sfx/player/footsteps/foot_grass02.wav") ,   // 61
            loadSound("./assets/sfx/player/footsteps/foot_grass03.wav") ,   // 62
            loadSound("./assets/sfx/player/footsteps/foot_grass04.wav") ,   // 63
            
            loadSound("./assets/sfx/player/footsteps/foot_metal01.wav") ,   // 64
            loadSound("./assets/sfx/player/footsteps/foot_metal02.wav") ,   // 65
            loadSound("./assets/sfx/player/footsteps/foot_metal03.wav") ,   // 66
            loadSound("./assets/sfx/player/footsteps/foot_metal04.wav") ,   // 67
            
            loadSound("./assets/sfx/player/footsteps/foot_water01.wav") ,   // 68
            loadSound("./assets/sfx/player/footsteps/foot_water02.wav") ,   // 69
            loadSound("./assets/sfx/player/footsteps/foot_water03.wav") ,   // 70
            loadSound("./assets/sfx/player/footsteps/foot_water04.wav") ,   // 71
            
            loadSound("./assets/sfx/player/footsteps/foot_wood01.wav") ,   // 72
            loadSound("./assets/sfx/player/footsteps/foot_wood02.wav") ,   // 73
            loadSound("./assets/sfx/player/footsteps/foot_wood03.wav") ,   // 74
            loadSound("./assets/sfx/player/footsteps/foot_wood04.wav") ,   // 75
            
            loadSound("./assets/sfx/ui/navigate01.wav") ,   // 76            
            loadSound("./assets/sfx/ui/navigate02.wav") ,   // 77
            
            loadSound("./assets/sfx/ui/key_type01.wav") ,   // 78
            loadSound("./assets/sfx/ui/key_type02.wav") ,   // 79
            loadSound("./assets/sfx/ui/key_type03.wav") ,   // 80
            
            loadSound("./assets/sfx/pistol/pistol_fire.wav") ,   // 81
            loadSound("./assets/sfx/pistol/pistol_reload.wav") ,   // 82
            
            loadSound("./assets/sfx/impact/impact_metal01.wav") ,   // 83
            loadSound("./assets/sfx/impact/impact_metal02.wav") ,   // 84
            loadSound("./assets/sfx/impact/impact_metal03.wav") ,   // 85
            loadSound("./assets/sfx/impact/impact_metal04.wav") ,   // 86
            loadSound("./assets/sfx/impact/impact_metal05.wav") ,   // 87
            
            loadSound("./assets/sfx/impact/impact_wood01.wav") ,   // 88
            loadSound("./assets/sfx/impact/impact_wood02.wav") ,   // 89
            loadSound("./assets/sfx/impact/impact_wood03.wav") ,   // 90
            loadSound("./assets/sfx/impact/impact_wood04.wav") ,   // 91
            loadSound("./assets/sfx/impact/impact_wood05.wav") ,   // 92
            
            loadSound("./assets/sfx/impact/impact_foliage01.wav") ,   // 93
            loadSound("./assets/sfx/impact/impact_foliage02.wav") ,   // 94
            loadSound("./assets/sfx/impact/impact_foliage03.wav") ,   // 95
            loadSound("./assets/sfx/impact/impact_foliage04.wav") ,   // 96
            loadSound("./assets/sfx/impact/impact_foliage05.wav") ,   // 97
            
            loadSound("./assets/sfx/impact/impact_glass01.wav") ,   // 
            loadSound("./assets/sfx/impact/impact_glass02.wav") ,   // 
            loadSound("./assets/sfx/impact/impact_glass03.wav") ,   // 
            loadSound("./assets/sfx/impact/impact_glass04.wav") ,   // 
            loadSound("./assets/sfx/impact/impact_glass05.wav") ,   // 
            
            loadSound("./assets/sfx/impact/impact_default01.wav") ,   // 98
            loadSound("./assets/sfx/impact/impact_default02.wav") ,   // 99
            loadSound("./assets/sfx/impact/impact_default03.wav") ,   // 100
            loadSound("./assets/sfx/impact/impact_default04.wav") ,   // 101
            loadSound("./assets/sfx/impact/impact_default05.wav") ,   // 102
            
            loadSound("./assets/sfx/risker/risker_fire.wav") ,   // 103
            loadSound("./assets/sfx/risker/risker_reload.wav") ,   // 104
            loadSound("./assets/sfx/risker/risker_rechamber.wav") ,   // 105
            
            loadSound("./assets/sfx/player/footsteps/mech_footstep01.wav") ,   // 106
            loadSound("./assets/sfx/player/footsteps/mech_footstep02.wav") ,   // 107
            
            loadSound("./assets/sfx/player/mech_torso_move.wav") ,   // 108
            
            loadSound("./assets/sfx/player/breathing_lite01.wav") ,   // 109
            loadSound("./assets/sfx/player/breathing_lite02.wav") ,   // 110
            loadSound("./assets/sfx/player/breathing_lite03.wav") ,   // 111
            
            loadSound("./assets/sfx/player/breathing_heavy01.wav") ,   // 112
            loadSound("./assets/sfx/player/breathing_heavy02.wav") ,   // 113
            loadSound("./assets/sfx/player/breathing_heavy03.wav") ,   // 114
            
            loadSound("./assets/sfx/bullet_zing01.wav") ,   // 115
            loadSound("./assets/sfx/bullet_zing02.wav") ,   // 116
            loadSound("./assets/sfx/bullet_zing03.wav") ,   // 117
            
            loadSound("./assets/sfx/player/healthpack_pickup.wav") ,   // 120
            
            loadSound("./assets/sfx/player/speech/allied/attack.wav") ,   // 121
            loadSound("./assets/sfx/player/speech/allied/cover_me.wav") ,   // 122
            loadSound("./assets/sfx/player/speech/allied/follow_me.wav") ,   // 123
            loadSound("./assets/sfx/player/speech/allied/get_out_of_my_way.wav") ,   // 124
            loadSound("./assets/sfx/player/speech/allied/get_us_killed.wav") ,   // 125
            loadSound("./assets/sfx/player/speech/allied/hold_position.wav") ,   // 126
            loadSound("./assets/sfx/player/speech/allied/ill_cover_you.wav") ,   // 127
            loadSound("./assets/sfx/player/speech/allied/taking_fire_help.wav") ,   // 128
            loadSound("./assets/sfx/player/speech/allied/you_take_lead.wav") ,   // 129
            
            
            loadSound("./assets/sfx/player/speech/axis/attack.wav") ,   // 130
            loadSound("./assets/sfx/player/speech/axis/cover_me.wav") ,   // 131
            loadSound("./assets/sfx/player/speech/axis/follow_me.wav") ,   // 132
            loadSound("./assets/sfx/player/speech/axis/get_out_of_my_way.wav") ,   // 133
            loadSound("./assets/sfx/player/speech/axis/get_us_killed.wav") ,   // 134
            loadSound("./assets/sfx/player/speech/axis/hold_position.wav") ,   // 135
            loadSound("./assets/sfx/player/speech/axis/ill_cover_you.wav") ,   // 136
            loadSound("./assets/sfx/player/speech/axis/taking_fire_help.wav") ,   // 137
            loadSound("./assets/sfx/player/speech/axis/you_take_lead.wav") ,   // 138
            
            loadSound("./assets/sfx/tank/tank_on.wav") ,   // 139
            loadSound("./assets/sfx/tank/tank_off.wav") ,   // 140
            loadSound("./assets/sfx/tank/tank_idle.wav") ,   // 141
            loadSound("./assets/sfx/tank/tank_shift1.wav") ,   // 142
            loadSound("./assets/sfx/tank/tank_shift2.wav") ,   // 143
            loadSound("./assets/sfx/tank/tank_shift3.wav") ,   // 144
            loadSound("./assets/sfx/tank/tank_revup.wav") ,   // 145
            loadSound("./assets/sfx/tank/tank_revdown.wav") ,   // 146
            loadSound("./assets/sfx/tank/tank_turret2.wav") ,   // 147
            loadSound("./assets/sfx/tank/tank_move.wav") ,   // 148
            loadSound("./assets/sfx/tank/tank_fire.wav") ,   // 149
            
            loadSound("./assets/sfx/ctf/flag_captured.wav") ,   // 150
            loadSound("./assets/sfx/ctf/flag_stolen.wav") ,   // 151
            loadSound("./assets/sfx/ctf/flag_returned.wav") ,   // 152
            loadSound("./assets/sfx/ctf/enemy_flag_captured.wav") ,   // 153
            loadSound("./assets/sfx/ctf/enemy_flag_stolen.wav") ,   // 154
            
            loadSound("./assets/sfx/mg42/mg42_fire01.wav") ,   // 155
            loadSound("./assets/sfx/mg42/mg42_fire02.wav") ,   // 156
            
            loadSound("./assets/sfx/radio/static01.wav") ,   // 157
            loadSound("./assets/sfx/radio/static02.wav") ,   // 158
            
            loadSound("./assets/sfx/obj/allied_mission_accomplished_01.wav") ,   // 159
            loadSound("./assets/sfx/obj/allied_mission_accomplished_02.wav") ,   // 160
            loadSound("./assets/sfx/obj/allied_mission_accomplished_03.wav") ,   // 161
            
            loadSound("./assets/sfx/obj/axis_mission_accomplished_01.wav") ,   // 162
            loadSound("./assets/sfx/obj/axis_mission_accomplished_02.wav") ,   // 163
            loadSound("./assets/sfx/obj/axis_mission_accomplished_03.wav") ,   // 164
            
            loadSound("./assets/sfx/door/door_open01.wav") ,   // 165
            loadSound("./assets/sfx/door/door_open02.wav") ,   // 166
            loadSound("./assets/sfx/door/door_open03.wav") ,   // 167
            loadSound("./assets/sfx/door/door_open04.wav") ,   // 168
            
            loadSound("./assets/sfx/door/door_close01.wav") ,   // 169
            loadSound("./assets/sfx/door/door_close02.wav") ,   // 170
            
            loadSound("./assets/sfx/door/door_close_blocked01.wav") ,   // 171
            loadSound("./assets/sfx/door/door_close_blocked02.wav") ,   // 172
            loadSound("./assets/sfx/door/door_close_blocked03.wav") ,   // 173
            
            loadSound("./assets/sfx/door/door_open_blocked01.wav") ,   // 174
            
            loadSound("./assets/sfx/grenade/smoke_grenade.wav") ,   // 175
            loadSound("./assets/sfx/fire01.wav") ,   // 176
            loadSound("./assets/sfx/flamethrower/start_firing.wav") ,   // 177
            
            loadSound("./assets/sfx/hammer/hammer.wav") ,   // 178
            

            loadSound("./assets/sfx/bullet_shell01.wav") ,   // 179
            loadSound("./assets/sfx/bullet_shell02.wav") ,   // 180
            loadSound("./assets/sfx/bullet_shell03.wav") ,   // 181
            loadSound("./assets/sfx/shotgun_shell01.wav") ,  // 182
        };
    };

    private static SoundSystem soundSystem;
    
    public static void init(ClientSeventhConfig cfg) {
        try {
            Cons.println("Initializing the sound subsystem...");
            Cons.getImpl().addCommand(getVolumeCommand());
            
            config = cfg;
            volume = config.getVolume();
            
            SoundSystemConfig.setLogger(new SoundSystemLogger() {
                @Override
                public void errorMessage(String message, String error, int code) {
                    Cons.println("*** Error in the sound system: " + message + " (" + error + ") :: " + code);
                }
                @Override
                public void message(String message, int code) {
                    Cons.println("*** Sound system: " + message + " :: " + code);
                }
                
                @Override
                public void importantMessage(String message, int code) {
                    Cons.println("*** (I) Sound system: " + message + " :: " + code);
                }
            });
            
            SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec( "wav", CodecWav.class );
            SoundSystemConfig.setDefaultFadeDistance(32f * 3.f);//10000f);
            SoundSystemConfig.setMasterGain(volume);                    
            SoundSystemConfig.setNumberNormalChannels(cfg.getNumberOfSoundChannels());
            
            soundSystem = new SoundSystem(LibraryLWJGLOpenAL.class);
            soundSystem.checkFadeVolumes();
            
            setVolume(volume);        
            
            LeoObject soundCfgs = Scripting.loadScript(Scripting.newSandboxedRuntime(), config.getSoundConfigPath());
            if(LeoObject.isTrue(soundCfgs)) {
                LeoMap map = soundCfgs.as();
                for(Entry<LeoObject, LeoObject> e: map.entrySet()) {
                    SoundConfig sndCfg = LeoObject.fromLeoObject(e.getValue(), SoundConfig.class);
                    soundConfigs.put(e.getKey().toString(), sndCfg);
                }
            }
            
            for(int i = 0; i < channels.length; i++) {
                channels[i] = createChannel();
            }
            
            Cons.println("Sound system online!");
        }
        catch(SoundSystemException e) {
            Cons.println("Unable to initialize the sound plugins.");
        }
    }
    
    /**
     * @param volume the volume to set
     */
    public static void setVolume(float volume) {
        Sounds.volume = volume;        
        soundSystem.setMasterVolume(volume);
        if(config!=null) {            
            config.setVolume(volume);
        }
    }
    
    /**
     * @return the volume
     */
    public static float getVolume() {
        return volume;
    }
    
    
    private static Command getVolumeCommand() {
        return new Command("volume") {
            
            @Override
            public void execute(Console console, String... args) {
                if(args == null || args.length < 1) {
                    console.println(volume);
                }
                else {
                    try {
                        float v = Float.parseFloat(args[0]);
                        setVolume(v);
                    }
                    catch(Exception e) {
                        console.println("*** Must be a number between 0 and 1");
                    }
                }
            }
        };
    }
    
    public static void setPosition(Vector2f pos) {
        if(soundSystem!=null) {
            listenerPosition.set(pos);
            soundSystem.setListenerPosition(pos.x, pos.y, 0);
        }
    }
    
    public static Vector2f getPosition() {
        return listenerPosition;
    }
    
    public static synchronized void destroy() {
        if(soundSystem!=null) {
            for(SoundBuffer sound : loadedSounds.values()) {
                sound.destroy();
            }
            loadedSounds.clear();
            
            soundSystem.removeTemporarySources();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            try {soundSystem.cleanup(); } catch(Exception e) {}
            
        }
    }
    
    
    
    /**
     * Attempts to load a {@link Sound}
     * 
     * @param soundFile
     * @return the {@link Sound} if loaded successfully
     */
    public static synchronized Sound loadSound(String soundFile) {
        try {            
            Sound sound = null;
            if(loadedSounds.containsKey(soundFile)) {
                sound = loadedSounds.get(soundFile).newSound();
            }
            else {
                SoundConfig soundCfg = soundConfigs.get(parseName(soundFile));
                if(soundCfg == null) {
                    soundCfg = defaultSoundConfig;
                }
                
                SoundBuffer buffer = new SoundBuffer(soundSystem, soundFile, random, soundCfg);
                loadedSounds.put(soundFile, buffer);
                
                sound = buffer.newSound();
            }
            
            sound.setVolume(volume);
            return sound;
        }
        catch(Exception e) {
            Cons.println("*** Error loading sound: " + soundFile + " - " + e);
        }
        
        return null;
    }
    
    private static String parseName(String soundFile) {
        int startIndex = soundFile.lastIndexOf("/") + 1;
        int endIndex   = soundFile.lastIndexOf(".");
        
        while(endIndex > startIndex && Character.isDigit(soundFile.charAt(endIndex-1))) {
            endIndex--;
        }
        
        return soundFile.substring(startIndex, endIndex);
    }
    
    
    /**
     * Unloads the sound from system memory.
     * 
     * @param soundFile
     */
    public static synchronized void unloadSound(String soundFile) {        
        SoundBuffer buffer = loadedSounds.remove(soundFile);
        if(buffer!=null) {
            buffer.destroy();
        }
    }       
    
    public static Sound findFreeSound(int soundIndex) {
        for(int i = 0; i < channels.length; i++) {
            Sound[] sounds = channels[i];        
            Sound sound = sounds[soundIndex];
            if(!sound.isPlaying()) {
                return sound;
            }
        }        
        return null;
    }
    
    public static Sound startPlaySound(int[] soundBank, long channelId, Vector2f pos) {
        return startPlaySound(soundBank, channelId, pos.x, pos.y);
    }
    
    public static Sound startPlaySound(int[] soundBank, long channelId, float x, float y) {
        int index = random.nextInt(soundBank.length);
        int soundIndex = soundBank[index];
        
        Sound[] sounds = channels[ (int)channelId % channels.length];        
        Sound sound = sounds[soundIndex];
        
        sound.setVolume(volume); // TODO global config
        sound.play(x,y);    
        return sound;
    }
    
    
    public static Sound playGlobalSound(NetSound snd) {
        return playGlobalSound(snd.getSoundType());
    }
    
    public static Sound playGlobalSound(SoundType type) {
        return playGlobalSound(soundBank(type), 1.0f);
    }
    
    /**
     * Plays the sound right next to the sound listener so it is
     * always audible.
     * @param soundBank
     * @return the {@link Sound}
     */
    public static Sound playGlobalSound(int[] soundBank) {
        return playGlobalSound(soundBank, 1.0f);
    }
    
    
    /**
     * Plays the sound right next to the sound listener so it is
     * always audible.
     * @param soundBank
     * @return the {@link Sound}
     */
    public static Sound playGlobalSound(int[] soundBank, float damp) {
        float x = 0;
        float y = 0;
        if(soundSystem != null) {
            ListenerData data = soundSystem.getListenerData();
            x = data.position.x;
            y = data.position.y;
        }
        return playFreeSound(soundBank, x, y, damp);
    }
    
    /**
     * Plays a sound at a particular position.
     * 
     * @param soundBank
     * @param channelId
     * @param pos
     * @return
     */
    public static Sound playSound(int[] soundBank, long channelId, Vector2f pos) {
        return playSound(soundBank, channelId, pos, false);
    }
    
    /**
     * Plays a sound at a particular position.
     * 
     * @param soundBank
     * @param channelId
     * @param pos
     * @param loop
     * @return
     */
    public static Sound playSound(int[] soundBank, long channelId, Vector2f pos, boolean loop) {
        return playSound(soundBank, channelId, pos.x, pos.y, loop);
    }
    
    /**
     * Plays a sound at a particular position.
     * 
     * @param soundBank
     * @param channelId
     * @param x
     * @param y
     * @return
     */
    public static Sound playSound(int[] soundBank, long channelId, float x, float y) {
        return playSound(soundBank, channelId, x, y, false);
    }
    
    /**
     * Plays a sound at a particular position.
     * 
     * @param soundBank
     * @param channelId
     * @param x
     * @param y
     * @param loop
     * @return
     */
    public static Sound playSound(int[] soundBank, long channelId, float x, float y, boolean loop) {
        int index = random.nextInt(soundBank.length);
        int soundIndex = soundBank[index];
        
        Sound[] sounds = channels[ (int)channelId % channels.length];        
        Sound sound = sounds[soundIndex];
        if(!sound.isPlaying()) {            
            sound.setVolume(volume); // TODO global config
            sound.play(x,y, loop);
        }
        
        return sound;
    }

    public static Sound playFreeSound(int[] soundBank, Vector2f pos) {
        return playFreeSound(soundBank, pos.x, pos.y);
    }
    
    public static Sound playFreeSound(int[] soundBank, float x, float y) {
        return playFreeSound(soundBank, x, y, 1.0f);
    }
    
    public static Sound playFreeSound(int[] soundBank, float x, float y, float damp) {
        if(soundBank != null) {
            int index = random.nextInt(soundBank.length);
            int soundIndex = soundBank[index];
            Sound snd = findFreeSound(soundIndex);
            if(snd!=null) {
                snd.setVolume(volume*damp); // TODO global config
                snd.play(x,y);
            }            
            return snd;
        }
        
        return null;
    }
    
    public static Sound playSound(byte soundId, float x, float y) {
        return playSound(soundId, x, y, 1.0f);
    }
    
    public static Sound playSound(byte soundId, float x, float y, float damp) {
        SoundType type = SoundType.fromNet(soundId);
        return playSound(type, x, y, damp);
    }
    
    public static Sound playSound(NetSound sound, float x, float y) {
        return playSound(sound, x, y, 1.0f);
    }
    
    public static Sound playSound(NetSound sound, float x, float y, float damp) {
        SoundType type = sound.getSoundType();
        return playSound(type, x, y, damp);
    }
    
    public static Sound playSpeechSound(int teamId, byte speech, float x, float y) {
        Sound sound = null;
        if(speech > -1 && speech < axisSpeeches.length) {
            
            if(teamId == ClientTeam.AXIS.getId()) {
                sound = playFreeSound(axisSpeeches[speech], x, y);
            }
            else if(teamId == ClientTeam.ALLIES.getId()) {
                sound = playFreeSound(alliedSpeeches[speech], x, y);
            }
        }
        
        return (sound);
    }
    
    /**
     * @param type
     * @return the corresponding sound bank for the {@link SoundType}
     */
    public static int[] soundBank(SoundType type) {
        int[] sound = null;
        switch(type) {
        case EMPTY_FIRE: 
            sound = emptyFireSnd;
            break;
            
        case THOMPSON_FIRE: 
            sound = thompsonFire;
            break;
        case THOMPSON_RELOAD: 
            sound = thompsonReload;
            break;
        
        
        case EXPLOSION:
            sound = explodeSnd;
            break;        
        case FIRE:
            sound = fire;
            break;
        case RPG_FIRE:
            sound = rocketFire;
            break;
            
        case GRENADE_PINPULLED:
            sound = grenadePinPulled;
            break;            
        case GRENADE_THROW:
            sound = grenadeThrow;
            break;
        case SMOKE_GRENADE:
            sound = smokeGrenade;
            break;
            
        case SHOTGUN_FIRE:
            sound = shotgunFire;
            break;
        case SHOTGUN_RELOAD: 
            sound = shotgunReload;
            break;
        case SHOTGUN_PUMP:
            sound = shotgunPump;
            break;            
            
        case SPRINGFIELD_FIRE:
            sound = springfieldFire;            
            break;
        case SPRINGFIELD_RECHAMBER:
            sound = springfieldRechamber;
            break;
        case SPRINGFIELD_RELOAD: 
            sound = springfieldReload;
            break;
        
        case RISKER_FIRE:
            sound = riskerFire;            
            break;
        case RISKER_RECHAMBER:
            sound = riskerRechamber;
            break;
        case RISKER_RELOAD: 
            sound = riskerReload;
            break;
            
        case M1_GARAND_FIRE: 
            sound = m1Fire;
            break;
        case M1_GARAND_LAST_FIRE:
            sound = m1FireLast;
            break;
        case M1_GARAND_RELOAD:
            sound = m1Reload;
            break;
            
        case KAR98_FIRE:
            sound = kar98Fire;
            break;
        case KAR98_RECHAMBER:
            sound = kar98Rechamber;
            break;
        case KAR98_RELOAD:
            sound = kar98Reload;
            break;
        
        case MP44_FIRE:
            sound = mp44Fire;
            break;
        case MP44_RELOAD:
            sound = mp44Reload;
            break;
            
        case MP40_FIRE:
            sound = mp40Fire;
            break;
        case MP40_RELOAD:
            sound = mp40Reload;
            break;
            
        case FLAMETHROWER_SHOOT:
            sound = flameThrowerShoot;
            break;
            
        case SURFACE_GRASS:            
            sound = grassWalk;            
            break;
        case SURFACE_METAL:
            sound = metalWalk;
            break;
        case SURFACE_NORMAL:
            sound = normalWalk;
            break;
        case SURFACE_WATER:
            sound = waterWalk;
            break;
        case SURFACE_WOOD:
            sound = woodWalk;
            break;
        case SURFACE_DIRT: 
            sound = dirtWalk;
            break;
        case SURFACE_SAND: 
            sound = dirtWalk;
            break;
        case WEAPON_SWITCH:
            sound = weaponSwitch;
            break;
        case BULLET_SHELL:
            sound = bulletShell;
            break;
        case SHOTGUN_SHELL:
            sound = shotgunShell;
            break;
        case RUFFLE:
            sound = ruffle;
            break;
        case BOMB_TICK:
            sound = bombTick;
            break;
        case BOMB_PLANT:
            sound = bombPlant;
            break;
        case BOMB_DISARM:
            sound = bombDisarm;
            break;            
        case WEAPON_DROPPED:
            sound = weaponDrop;
            break;
        case WEAPON_PICKUP:        
            sound = weaponPickupSnd;
            break;
        case AMMO_PICKUP:
            sound = ammoPickupSnd;
            break;
        case MELEE_SWING:
            sound = meleeSwing;
            break;
        case MELEE_HIT:
            sound = meleeHit;
            break;
        case PISTOL_FIRE:
            sound = pistolFire;
            break;
        case PISTOL_RELOAD:
            sound = pistolReload;
            break;
        case HAMMER_SWING: 
            sound = hammer;
            break;
        case UI_ELEMENT_HOVER:
            sound = uiHover;
            break;
        case UI_ELEMENT_SELECT:
            sound = uiSelect;
            break;            
        case UI_NAVIGATE:
            sound = uiNavigate;
            break;                
        case UI_KEY_TYPE:
            sound = uiKeyType;
            break;
        case IMPACT_METAL:
            sound = impactMetal;
            break;
        case IMPACT_DEFAULT:
            sound = impactDefault;
            break;
        case IMPACT_FOLIAGE:            
            sound = impactFoliage;
            break;        
        case IMPACT_WOOD:
            sound = impactWood;
            break;
        case IMPACT_GLASS:
            sound = impactGlass;
            break;
        case IMPACT_FLESH:
            sound = hit;
            break;
        case HIT_INDICATOR:
            sound = hitIndication;
            break;
        case HEADSHOT:
            sound = headshot;
            break;
        case TANK_ON: 
            sound = tankOn;
            break;
        case TANK_OFF: 
            sound = tankOff;
            break;
        case TANK_REV_UP: 
            sound = tankRevUp;
            break;            
        case TANK_REV_DOWN: 
            sound = tankRevDown;
            break;            
        case TANK_IDLE: 
            sound = tankIdle;
            break;            
        case TANK_SHIFT: 
            sound = tankShift;
            break;            
        case TANK_TURRET_MOVE: 
            sound = tankTurret;
            break;                    
        case TANK_MOVE: 
            sound = tankMove;
            break;        
        case TANK_FIRE:
            sound = tankFire;
            break;
        case BREATH_HEAVY: 
            sound = breadthHeavy;
            break;
        case BREATH_LITE:
            sound = breadthLite;
            break;
        case HEALTH_PACK_PICKUP:
            sound = healthPackPickup;
            break;
        case ENEMY_FLAG_CAPTURED:
            sound = enemyFlagCaptured;
            break;
        case ENEMY_FLAG_STOLEN:
            sound = enemyFlagStolen;
            break;
        case FLAG_CAPTURED:
            sound = flagCaptured;
            break;
        case FLAG_RETURNED:
            sound = flagReturned;
            break;
        case FLAG_STOLEN:
            sound = flagStolen;
            break;
        case MG42_FIRE:
            sound = mg42Fire;
            break;
        case RADIO_STATIC:
            sound = radioStatic;
            break;
        case ALLIED_VICTORY:
            sound = alliedVictory;
            break;
        case AXIS_VICTORY:
            sound = axisVictory;
            break;
        case DOOR_OPEN:
            sound = doorOpen;
            break;
        case DOOR_CLOSE:
            sound = doorClose;
            break;
        case DOOR_CLOSE_BLOCKED:
            sound = doorCloseBlocked;
            break;
        case DOOR_OPEN_BLOCKED:
            sound = doorOpenBlocked;
            break;
        case MUTE:
            
        default:
            break;
        }
        return sound;
    }
    
    public static Sound playSound(SoundType type, float x, float y) {
        return playSound(type, x, y, 1.0f);
    }
    
    
    /**
     * Play a sound at the specified location
     * 
     * @param type
     * @param x
     * @param y
     * @return the {@link Sound}
     */
    public static Sound playSound(SoundType type, float x, float y, float damp) {
        Sound sound = null;
        int[] soundBank = soundBank(type);
        if(soundBank != null) {
            sound = playFreeSound(soundBank, x, y, damp);
        }
        
        return sound;
    }

}
