package Events;

import java.util.HashMap;
import java.util.Map;

public class MapOfDescendantIDs {
    public static void returnDescendantName() {
        {
            Map<String, String> descendantMap = new HashMap<>();
            descendantMap.put("101000001", "Lepic");
            descendantMap.put("101000002", "Ajax");
            descendantMap.put("101000003", "Viessa");
            descendantMap.put("101000004", "Ultimate Lepic");
            descendantMap.put("101000005", "Jayber");
            descendantMap.put("101000006", "Bunny");
            descendantMap.put("101000007", "Ultimate Ajax");
            descendantMap.put("101000008", "Freyna");
            descendantMap.put("101000009", "Gley");
            descendantMap.put("101000010", "Ultimate Viessa");
            descendantMap.put("101000011", "Sharen");
            descendantMap.put("101000012", "Blair");
            descendantMap.put("101000013", "Valby");
            descendantMap.put("101000014", "Kyle");
            descendantMap.put("101000015", "Esiemo");
            descendantMap.put("101000016", "Enzo");
            descendantMap.put("101000017", "Yujin");
            descendantMap.put("101000018", "Luna");
            descendantMap.put("101000019", "Ultimate Bunny ");
            descendantMap.put("101000020", "Ultimate Gley");
            descendantMap.put("101000021", "null");
            descendantMap.put("101000022", "Ultimate Valby");
            profileSlashCommand.descendant_name = descendantMap.get(profileSlashCommand.descendant_id);

        }

    }
    public static void returnDescendantPicture(){
        Map<String, String> descendantPictureMap = new HashMap<>();
        descendantPictureMap.put("101000001", "https://open.api.nexon.com/static/tfd/img/6cecbb40acd1b4479a44caaf22817751");
        descendantPictureMap.put("101000002", "https://open.api.nexon.com/static/tfd/img/74d88097b3547c406a36651c9378638c");
        descendantPictureMap.put ("101000003", "https://open.api.nexon.com/static/tfd/img/b2be3dc734d2fb10e694de951a5d1079");
        descendantPictureMap.put ("101000004", "https://open.api.nexon.com/static/tfd/img/e2f1ec94648d91eede78f377c3b7aa19");
        descendantPictureMap.put ("101000005", "https://open.api.nexon.com/static/tfd/img/79c5cbb60e2e06209a0a515775cfa3b6");
        descendantPictureMap.put ("101000006", "https://open.api.nexon.com/static/tfd/img/eaffb014c98c36d8b30d90d7778bb840");
        descendantPictureMap.put ("101000007", "https://open.api.nexon.com/static/tfd/img/118d86964484b25593b9a4dfe62e96c1");
        descendantPictureMap.put ("101000008", "https://open.api.nexon.com/static/tfd/img/6737a4c79a8a2fe617cb458031e11358");
        descendantPictureMap.put ("101000009", "https://open.api.nexon.com/static/tfd/img/d72fa90551e23916f18a81dbd4030d8f");
        descendantPictureMap.put ("101000010", "https://open.api.nexon.com/static/tfd/img/665a53a9b478437b13f7d6ae5f5322db");
        descendantPictureMap.put ("101000011", "https://open.api.nexon.com/static/tfd/img/f14fdb3a55fc96e409a10b5dfa2b31b0");
        descendantPictureMap.put ("101000012", "https://open.api.nexon.com/static/tfd/img/1a5ae4f83650ec541226f55c06deef64");
        descendantPictureMap.put ("101000013", "https://open.api.nexon.com/static/tfd/img/f1236f2af539bd14c431b5befdd120f4");
        descendantPictureMap.put ("101000014", "https://open.api.nexon.com/static/tfd/img/2ae6db799a211e64aa6a0cd1494c1b0d");
        descendantPictureMap.put ("101000015", "https://open.api.nexon.com/static/tfd/img/4f2cbea689b4118dc58895e5e0771270");
        descendantPictureMap.put ("101000016", "https://open.api.nexon.com/static/tfd/img/dd903ca869faef90ddbe9a64127c8ca1");
        descendantPictureMap.put ("101000017", "https://open.api.nexon.com/static/tfd/img/2bb766e96b8aa777faf6ef1b5afcbc3f");
        descendantPictureMap.put ("101000018", "https://open.api.nexon.com/static/tfd/img/d4e6462f87513fe6652633620b7f8d19");
        descendantPictureMap.put ("101000019", "https://open.api.nexon.com/static/tfd/img/d4e6462f87513fe6652633620b7f8d19");
        descendantPictureMap.put ("101000020", "https://open.api.nexon.com/static/tfd/img/c4c10b88e178305521d044f5a969583d");
        descendantPictureMap.put ("null1", "null");
        descendantPictureMap.put ("101000022", "https://open.api.nexon.com/static/tfd/img/8ae81e3d05a981d5da24c26c09106330");
        descendantPictureMap.put ("null3", "null");
        descendantPictureMap.put ("null4", "null");
        profileSlashCommand.descendantPictureLink = descendantPictureMap.get(profileSlashCommand.descendant_id);
    }

}
