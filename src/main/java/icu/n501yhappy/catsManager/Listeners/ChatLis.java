package icu.n501yhappy.catsManager.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Random;

import static icu.n501yhappy.catsManager.CatsManager.Cats;

public class ChatLis implements Listener {
    static Random random= new Random();
    public static String translate(String text) {
        if (text.contains("喵")) return text;
        // 1. 添加语气词 "喵"
        text = text.replace("吗", "喵");
        text = text.replace("?", "喵?");
        text = text.replace("！", "喵！");
        text = text.replace("。", "喵。");
        text = text.replace("~", "nya~"); // 添加对波浪线的处理

        // 2. 人称代词转换
        text = text.replace("我", "人家");
        text = text.replace("你", "主人"); // 可以根据实际情况调整
        text = text.replace("他", "那家伙");
        text = text.replace("她", "那个女孩");
        text = text.replace("我们", "咱家"); // 添加 "我们" 的转换
        text = text.replace("你们", "各位主人");

        // 3. 添加一些可爱的后缀
        text = text.replace("了", "了喵");
        text = text.replace("呢", "呢喵");
        text = text.replace("呀", "呀喵");
        text = text.replace("啊", "啊喵");

        // 4. 随机添加 "喵"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (random.nextDouble() < 0.1) {
                sb.append("喵");
            }
        }
        text = sb.toString();

        text = text.replace("喜欢", "最喜欢");
        text = text.replace("讨厌", "不喜欢");
        text = text.replace("是", "是喵");
        text = text.replace("好", "好喵");
        text = text.replace("不", "才不");
        text = text.replace("要", "想要");
        text = text.replace("不要", "才不要");
        text = text.replace("知道", "知道啦喵");
        text = text.replace("真的", "真的喵");

        // 6. 处理负面词汇 (新增)
        text = text.replace("傻逼", "笨蛋喵");
        text = text.replace("坏", "不好喵");
        text = text.replace("笨", "笨笨的喵");
        text = text.replace("滚", "走开啦喵");
        text = text.replace("烦", "好烦喵");

        // 7. 30% 概率在末尾添加 "nya~"
        if (random.nextDouble() < 0.1) {
            text += " nya~";
        }

        return text;
    }
    @EventHandler
    public void OnChat(PlayerChatEvent event){
        if (Cats.contains(event.getPlayer())){
            event.setMessage(translate(event.getMessage()));
        }
    }
}
