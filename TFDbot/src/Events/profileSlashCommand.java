package Events;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import net.dv8tion.jda.api.EmbedBuilder;
//Class for communicating with Nexon's API and returning the requested data from the user.



public class profileSlashCommand extends ListenerAdapter {
    static String ouid;
    //Insert Nexon Api Key Below
    static String API_KEY = "";
    static String userName;
    static String platformType, platformImage;
    static int descendantLevel, masteryRankLevel, maxmodcapacity, modulecapacity, maxmodplus5;
    static String titlePrefixId, titleSuffixId;
    static String prefix, suffix;
    static String descendant_id, descendant_name;
    static List<Module> modules = new ArrayList<>();
    static String moduleId;
    static String characters, numbers;
    static StringBuilder firstHalfResult = new StringBuilder();
    static StringBuilder secondHalfResult = new StringBuilder();
    static String descendantPictureLink;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("profile")) return;
        OptionMapping playername = event.getOption("playername");
        assert playername != null;
        String playernamef = playername.getAsString();
        EmbedBuilder replyEmbed = new EmbedBuilder();
        new Thread(() -> {
            getouid(playernamef);
            String moduleInfo = formatModuleInfo(modules);
            replyEmbed.setColor(Color.blue);
            replyEmbed.setAuthor( playernamef + " | Mastery Rank: " + masteryRankLevel +  " ", "https://tfd.nexon.com/en/main", platformImage);
            replyEmbed.setThumbnail(descendantPictureLink);
            replyEmbed.setDescription("*" + prefix + suffix + "*");
            replyEmbed.addField(descendant_name + " (Level: " + descendantLevel + ")", String.valueOf(firstHalfResult), true);
            replyEmbed.addField("Mod Capacity: " + modulecapacity +"/" + maxmodcapacity  , String.valueOf(secondHalfResult), true);

            event.reply("Here is the data you requested: ").queue();
            event.getChannel().sendMessageEmbeds(replyEmbed.build()).queue();
        }).start();
        //clears the string builder object
        firstHalfResult.setLength(0);
        secondHalfResult.setLength(0);}

    public static void getouid(String playernamef) {
        try {
            String[] input = playernamef.split("#");
            if (input.length == 2) {
                characters = input[0];
                System.out.println("Characters: " + characters);
                numbers = input[1];
                System.out.println("Numbers: " + numbers);

            }
            String urlString = "https://open.api.nexon.com/tfd/v1/id?user_name=" + characters + "%23" + numbers;
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);
            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            ouid = jsonResponse.getString("ouid");

            if (ouid != null && !ouid.isEmpty()) {
                getbasic(ouid);
                getdescendantinfo(ouid);
                System.out.println(ouid);
            } else {
                System.out.println("OUID is null or empty");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void getbasic(String ouid) {
        try {
            String urlString = "https://open.api.nexon.com/tfd/v1/user/basic?ouid=" + ouid;
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);
            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            parseJsonBasic(response.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void getdescendantinfo(String ouid) {
        try {
            String urlString = "https://open.api.nexon.com/tfd/v1/user/descendant?ouid=" + ouid;
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);
            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            parseJsonDescendant(response.toString());
            parseJsonModule(response.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void parseJsonBasic(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        userName = jsonObject.optString("user_name", "N/A");
        platformType = jsonObject.optString("platform_type", "N/A");
        masteryRankLevel = jsonObject.optInt("mastery_rank_level", 0);
        titlePrefixId = jsonObject.optString("title_prefix_id", "No Prefix Equipped");
        titleSuffixId = jsonObject.optString("title_suffix_id", "No Suffix Equipped");

        switch (platformType) {
            case "Steam" -> platformImage = "https://i.imgur.com/3PN3bJg.png";
            case "Xbox" -> platformImage =  "https://i.imgur.com/gWrQrfn.png";
            case "PlayStation" -> platformImage = "https://i.imgur.com/ZYltjTF.png";
        }

        System.out.println("Username: " + userName);
        System.out.println("Platform Type: " + platformType);
        System.out.println("Mastery Rank Level: " + masteryRankLevel);
        System.out.println("Title Prefix ID: " + titlePrefixId);
        System.out.println("Title Suffix ID: " + titleSuffixId);
        if (!Objects.equals(titlePrefixId, "No Prefix Equipped")) {
            MapsOfIDs.returnTitlePrefix();
            System.out.println(prefix);
        } else {
            prefix = "";
        }
        if (!Objects.equals(titleSuffixId, "No Suffix Equipped")) {
            MapsOfIDs.returnTitleSuffix();
            System.out.println(suffix);
        } else {
            suffix = "";
        }
    }

    public static void parseJsonDescendant(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        descendant_id = jsonObject.optString("descendant_id", "Descendant not found");
        descendantLevel = jsonObject.optInt("descendant_level", 0);
        maxmodcapacity = jsonObject.optInt("module_max_capacity", 30);
        modulecapacity = jsonObject.optInt("module_capacity", 0);
        System.out.println("\nDescendant Level: " + descendantLevel);
        System.out.println("Max Mod Capacity: " + maxmodcapacity);
        System.out.println("Mod Capacity: " + modulecapacity);
        System.out.println("Descendant ID: " + descendant_id);
        if (!Objects.equals(descendant_id, "Descendant not found")) {
            MapOfDescendantIDs.returnDescendantName();
            MapOfDescendantIDs.returnDescendantPicture();
        } else {
            descendant_name = "Descendant Unknown.";
        }
    }

    public static void parseJsonModule(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray modulesArray = jsonObject.optJSONArray("module");

        modules.clear();

        if (modulesArray != null) {
            for (int i = 0; i < modulesArray.length(); i++) {
                JSONObject moduleObject = modulesArray.getJSONObject(i);
                String moduleSlotId = moduleObject.optString("module_slot_id");
                moduleId = moduleObject.optString("module_id");
                int moduleEnchantLevel = moduleObject.optInt("module_enchant_level");
                String moduleName = MapOfModuleIDs.returnModule();
                Module module = new Module(moduleSlotId, moduleId, moduleEnchantLevel, moduleName);
                modules.add(module);
            }
        }
    }

    public static String formatModuleInfo(List<Module> modules) {

        int midPoint = modules.size() / 2;

        for (int i = 0; i < midPoint; i++) {
            Module module = modules.get(i);
            appendModuleInfo(firstHalfResult, module);
        }

        for (int i = midPoint; i < modules.size(); i++) {
            Module module = modules.get(i);
            appendModuleInfo(secondHalfResult, module);
        }

        return "First Half:\n" + firstHalfResult + "\nSecond Half:\n" + secondHalfResult;
    }

    private static void appendModuleInfo(StringBuilder result, Module module) {
        String moduleSlotId = module.moduleSlotId();
        String specialSymbol = moduleSlotId.equals("Sub 1") ? " :orange_circle:" : "";
        String specialSymbol2 = moduleSlotId.equals("Skill 1") ? " :red_circle:" : "";

        if (Objects.equals(module.moduleSlotId(), "Sub 1")) {
            maxmodcapacity += module.moduleEnchantLevel();
            modulecapacity += module.moduleEnchantLevel();
            maxmodplus5 = maxmodcapacity + 5;
        }

        result.append(module.moduleName())
                .append("[")
                .append(module.moduleEnchantLevel())
                .append("]")
                .append(specialSymbol)
                .append(specialSymbol2)
                .append("\n");
    }

    public record Module(String moduleSlotId, String moduleId, int moduleEnchantLevel, String moduleName) {
    }
}
