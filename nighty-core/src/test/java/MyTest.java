import com.brokepal.nighty.core.cache.Cache;
import com.brokepal.nighty.core.util.MD5Util;
import org.junit.Test;

/**
 * Created by chenchao on 17/3/28.
 */
public class MyTest {
    @Test
    public void tt(){
        Cache.put("id",new String("sds"));
        String a = Cache.get("id");
        System.out.println(Cache.get("id"));
    }

    @Test
    public void tttt(){
        String passwordMD5 = MD5Util.string2MD5(MD5Util.string2MD5("aa") + "salt001");
        System.out.println(passwordMD5);
    }
}
