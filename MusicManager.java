// Jasarjan Singh
// CSE 143
// TA: Himani Nijhawan
// Assesment #2 Music Playlist
// This program represents a music playlist as a linked list 
// of songs. Listeners can not only get the next song in the 
// playlist, but they can also preview all the songs in the 
// playlist, remove any song from the playlist, and see all 
// the songs that they've removed in the order of most recently-removed.

import java.util.*;

public class MusicManager {
    private MusicNode frontCurrent;
    private MusicNode frontRemoved;

    // pre: throw an IllegalArgumentException 
    //      if the songs argument is an empty list
    // post: iterates over the list and constructs 
    //       a new linked list of MusicNode objects
    //       one node for each song 
    public MusicManager(List<String> songs) {
        if(songs.isEmpty()){
            throw new IllegalArgumentException();
        }
        frontCurrent = new MusicNode(songs.get(0));
        for (int i = 1; i < songs.size(); i++) {
            insertLast(frontCurrent, songs.get(i));
        }
    }

    // post: adds songs into the playlist in the same order
    //       in which they appear in the original list
    private void insertLast(MusicNode head, String song) {
        MusicNode current = head;
        while (current.next != null) {
            current = current.next; 
        }
        current.next = new MusicNode(song);
    }

    // post: takes a MusicNode as parameter and prints the songs
    //       one per line, each line indented by 4 spaces
    public void printSongs(MusicNode current) {
        while (current != null) {
            System.out.println("    " + current.song);
            current = current.next;
        }
    }

    // post: print the songs in the current playlist, 
    //       one per line, each line indented by 4 spaces
    public void printCurrent() {
        printSongs(frontCurrent);
    }

    // post: prints the removed songs in the order they were 
    //       removed, one per line, each line indented by 4 spaces 
    public void printRemoved() {
        printSongs(frontRemoved);
    }

    // post: returns true if the given song is in the current 
    //       playlist ignoring case, and false otherwise
    public boolean currentContains(String song) {
        MusicNode current = frontCurrent;
        while (current != null) {
            if (current.song.equalsIgnoreCase(song)) {
                return true;
            }
            current = current.next;
        }
        return false;

    }

    // post: returns true if the given song is in the list
    //       of removed songs ignoring case, and false otherwise
    public boolean removedContains(String song) {
        MusicNode current = frontRemoved;
        while (current != null) {
            if (song.equalsIgnoreCase(current.song)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // post: return true if there are 1 or more 
    //       songs left in the current playlist
    public boolean hasSongs() {
        return(frontCurrent != null);
    }

    // post: return the next song in the current playlist, 
    //       or null if there are no songs left
    public String nextSong() {
        if (frontCurrent == null) {
            return null;
        } else {
            return frontCurrent.song;
        }
    }

    // pre: throw an IllegalStateException if there 
    //      are no songs left to remove
    // post: removes the given song from the current playlist, 
    //       transferring the MusicNode to the front of the list of removed songs,
    //       maintaining the order of the songs and ignoring case when comparing
    public void remove(String song) {
        if (!hasSongs()) {
            throw new IllegalStateException();
        } 
        if (!currentContains(song)) {
            throw new IllegalArgumentException();
        }
        if (frontCurrent.song.equalsIgnoreCase(song)) {
            frontCurrent = frontCurrent.next;
        } else {
            MusicNode current = frontCurrent;
            while (!current.next.song.equalsIgnoreCase(song)) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
            }
        }
        MusicNode newNode = new MusicNode(song);
        newNode.next = frontRemoved;
        frontRemoved = newNode;
    }
}
