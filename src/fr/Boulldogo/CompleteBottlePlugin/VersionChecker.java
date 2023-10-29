package fr.Boulldogo.CompleteBottlePlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionChecker {

    private final String repoOwner;
    private final String repoName;
    private final String currentVersion;

    public VersionChecker(String repoOwner, String repoName, String currentVersion) {
        this.repoOwner = repoOwner;
        this.repoName = repoName;
        this.currentVersion = currentVersion;
    }

    public void checkForNewVersion() {
        try {
            String latestVersion = getLatestReleaseTag();
            if (latestVersion != null && latestVersion.equals(currentVersion)) {
                System.out.println("Vous utilisez la dernière version de CompleteBottlePlugin.");
            } else if (latestVersion != null) {
                System.out.println("Une nouvelle version de CompleteBottlePlugin est disponible !");
            } else {
                System.out.println("Impossible de vérifier la dernière version de CompleteBottlePlugin.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLatestReleaseTag() throws IOException {
        URL url = new URL("https://api.github.com/repos/" + repoOwner + "/" + repoName + "/releases/latest");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();

            return content.toString();
        } else {
            throw new IOException("Failed to fetch data from GitHub API. Response code: " + responseCode);
        }
    }
}
