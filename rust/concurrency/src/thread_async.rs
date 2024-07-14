

#[cfg(test)]
mod thread_async {
    use std::thread;
    use std::time::Duration;
    use futures::executor::block_on;

    #[test]
    fn test_call_sync_method() {
        println!("calling rust out");
        block_on(echo());
        println!("calling rust finished");
    }

    async fn echo() {
        println!("rust out!");
    }

    struct Song {
        author: String,
        name: String,
    }

    async fn learn_song() -> Song {
        Song{
            author: "曲婉婷".to_string(),
            name: String::from("《我的歌声里》"),
        }
    }

    async fn sing_song(song: Song) {
        println!("献上一曲歌曲 {} 的 {} ~ {}",
        song.author, song.name, "你存在我深深的脑海里...");
    }

    async fn dance() {
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        println!("动起来~");
        let mut i: i32 = 0;
        for j in 0..10000000 {
            i = i + 1;
        }

        println!("累了, 跳不动了... 共 {} 次", &i);
    }

    async fn learn_sing_and_song() {
        let song = learn_song().await;
        sing_song(song).await;
    }

    async fn singing() {
        let dc = dance();
        let song = learn_sing_and_song();

        futures::join!(dc, song);
    }

    #[test]
    fn test_sing_song_dance() {
        block_on(singing());
    }
}