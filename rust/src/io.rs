
#[cfg(test)]
pub mod io {
    use tokio::fs::File;
    use tokio::io::{AsyncBufReadExt, BufReader};

    #[tokio::test(flavor = "multi_thread", worker_threads = 1)]
    async fn test_read_file() -> tokio::io::Result<()> {
        let file = File::open("tests/1.txt").await?;

        let mut lines = BufReader::new(file).lines();

        while let Some(line) = lines.next_line().await? {
            println!("{}", line);
        }

        Ok(())
    }
}