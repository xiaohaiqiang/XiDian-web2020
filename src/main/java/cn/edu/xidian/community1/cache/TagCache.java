package cn.edu.xidian.community1.cache;

import cn.edu.xidian.community1.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("学习");
        program.setTags(Arrays.asList("作业", "实验", "考核", "竞赛", "论文", "资料", "选课"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("生活");
        framework.setTags(Arrays.asList("美食", "游玩", "二手交易", "兼职", "恋爱", "租房"));
        tagDTOS.add(framework);


        TagDTO server = new TagDTO();
        server.setCategoryName("工作");
        server.setTags(Arrays.asList("职业规划", "经验分享", "招聘", "求职", "宣讲会", "实习"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("娱乐");
        db.setTags(Arrays.asList("电子游戏", "影视", "小说", "运动", "综艺", "动漫"));
        tagDTOS.add(db);


        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
