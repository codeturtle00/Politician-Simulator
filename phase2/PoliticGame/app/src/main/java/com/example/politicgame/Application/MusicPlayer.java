package com.example.politicgame.Application;

import android.media.MediaPlayer;

import com.example.politicgame.R;

class MusicPlayer {

    // PoliticGameApp
    private PoliticGameApp app;

    // Music player settings
    private MediaPlayer musicPlayer;
    private boolean musicOn;
    private int currentTrack;

    // Song selections
    private final int TRACK_ONE = R.raw.pokemon_remix;
    private final int TRACK_TWO = R.raw.outset_island;
    private final int TRACK_THREE = R.raw.sov_techno;
    private final int TRACK_FOUR = R.raw.megalovania;

    // The music player will iterate over these songs as we change music
    private final int[] TRACK_ARRAY = new int[] {TRACK_ONE, TRACK_TWO, TRACK_THREE, TRACK_FOUR};

    MusicPlayer (PoliticGameApp app){
        this.app = app;
        currentTrack = 0; // Current track is TRACK_ONE
        musicPlayer = MediaPlayer.create(this.app, TRACK_ARRAY[currentTrack]);
        musicPlayer.setLooping(true);
        startMusic();
    }

    /**
     * Switch tracks for the MediaPlayer
     */
    void switchMusic() {
        if (currentTrack < TRACK_ARRAY.length - 1) {
            currentTrack++;
        } else {
            currentTrack = 0;
        }

        // Destroys the previous music player and creates a new one with the new track
        musicPlayer.release();
        musicPlayer = MediaPlayer.create(this.app, TRACK_ARRAY[currentTrack]);
        musicPlayer.setLooping(true);
        startMusic();
    }

    /**
     * Returns the current track number
     *
     * @return  The current track number
     */
    int getCurrentTrackNum() {
        return currentTrack + 1;
    }

    /**
     * Returns a boolean on if the music is currently on
     *
     * @return  Is the music currently on
     */
    boolean getMusicOn() {
        return musicOn;
    }

    /**
     * Start the music
     */
    private void startMusic() {
        musicPlayer.start();
        musicOn = true;
    }

    /**
     * Pause the music
     */
    private void pauseMusic() {
        musicPlayer.pause();
        musicOn = false;
    }

    /**
     * Turn the music on if the music was originally off, or turn the music off if it was already on
     */
    void toggleMusic() {
        if (musicOn) {
            pauseMusic();
        } else {
            startMusic();
        }
    }
}
