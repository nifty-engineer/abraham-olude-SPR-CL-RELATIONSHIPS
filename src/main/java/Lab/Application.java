package Lab;

import Lab.Model.Album;
import Lab.Model.Artist;
import Lab.Model.Song;
import Lab.Service.AlbumService;
import Lab.Service.ArtistService;
import Lab.Service.SongService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class);

        // get my beans
        AlbumService albumService = applicationContext.getBean(AlbumService.class);
        ArtistService artistService = applicationContext.getBean(ArtistService.class);
        SongService songService = applicationContext.getBean(SongService.class);

        // create some artists
        Artist ar1 = artistService.addArtist(new Artist("artist 1"));
        Artist ar2 = artistService.addArtist(new Artist("artist 2"));

        // create some albums
        Album al1 = albumService.addAlbum(new Album("album 1"));
        Album al2 = albumService.addAlbum(new Album("album 2"));

        // create some songs
        Song s1 = songService.addSong(new Song("song 1"));
        Song s2 = songService.addSong(new Song("song 2"));

        // attach the songs to an album
        albumService.addSongToAlbum(al1.getAlbumId(), s1);
        albumService.addSongToAlbum(al1.getAlbumId(), s2);
        System.out.println(songService.getAllSongs());
        System.out.println(albumService.getAllAlbums());

        System.out.println("Trying out the new custom query method that I added");

        Song s15 = new Song(15, new Album("album 3"));
        Song s16 = new Song(16, new Album("album 3"));
        List<Song> songsInAlbum = new ArrayList<>();
        songsInAlbum.add(s15);
        songsInAlbum.add(s16);
        albumService.addAlbum(new Album(12, "album 3", new Artist("artist 1"), songsInAlbum));

        System.out.println(songService.getAlbum(s15.getSongId()));
        System.out.println(songService.getAlbum(s16.getSongId()));

        
        // songService.addSong(s15);
        // songService.addSong(s16);
        // Song s3a = songService.addSong(new Song(15, new Album("album 3a")));
        // Song s3b = songService.addSong(new Song(16, new Album("album 3b")));
        // Song s3c = songService.addSong(new Song(10, "song 3", new Album("album 3")));
        // System.out.println(songService.getAlbum(s3a.getSongId()));
        // System.out.println(songService.getAlbum(s3b.getSongId()));
        // System.out.println(songService.getAlbum(s3c.getSongId()));
    }
}
