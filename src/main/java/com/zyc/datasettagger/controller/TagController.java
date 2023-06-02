package com.zyc.datasettagger.controller;

import com.zyc.common.data.TagInfo;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.exception.BizException;
import com.zyc.common.model.TagModel;
import com.zyc.common.model.page.ListPage;
import com.zyc.common.model.page.TagListPage;
import com.zyc.common.model.param.TagCreateParam;
import com.zyc.datasettagger.service.TagService;
import com.zyc.datasettagger.service.UserService;
import com.zyc.utils.convertor.TagConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author zyc
 * @version 1.0
 */
@RestController
@Slf4j
public class TagController {
    TagService tagService;
    UserService userService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/tag", consumes = MediaType.APPLICATION_JSON_VALUE)
    TagModel addTag(@RequestBody TagCreateParam param) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ObjectUtils.isEmpty(param)) {
            log.warn("[addTag]-param传参为空，操作者为{}", username);
            throw new BizException("标签信息不能为空", ReturnCode.INVALID_INPUT);
        }
        log.info("[addTag]-接收到参数param:{}", param);
        Integer userId = userService.getIdByUsername(username);
        TagInfo tagInfo = TagConvertor.param2Info(param, String.valueOf(userId));
        int i = tagService.addTag(tagInfo);
        if (i == 1) {
            log.info("[addTag]-插入tag成功，tag的id为{}", tagInfo.getTagId());
        } else {
            throw new BizException("新增标签失败", ReturnCode.RC999);
        }
        return TagConvertor.createFromInfo(tagInfo);
    }

    @GetMapping("/api/tag")
    TagListPage getTagByPagination(@RequestParam Integer page_num,
                                   @RequestParam Integer page_size,
                                   @RequestParam(required = false) String dataset_id) {
        // page_num和page_size的校验交给框架完成，业务逻辑无需校验
        if (ObjectUtils.isEmpty(dataset_id)) {
            // 全量查询
            return new TagListPage(page_num,0,new ArrayList<>(), page_size, 0);
        }
        ListPage<TagInfo> tagByPagination = tagService.getTagByPagination(dataset_id, page_num, page_size);
        int total = tagService.countAll(dataset_id);
        TagListPage tagListPage = new TagListPage();
        tagListPage.setTags(tagByPagination.getPageContent().stream().map(TagConvertor::createFromInfo).toList());
        tagListPage.setTotal(total);
        tagListPage.setCurPage(tagByPagination.getCurPage());
        tagListPage.setLimitation(tagByPagination.getLimitation());
        tagListPage.setPageSize(tagByPagination.getPageSize());
        return tagListPage;
    }

    @RequestMapping("/api/tag/{id}")
    String deleteTagById(@PathVariable String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.error("[deleteTagById]-id为空");
            throw new BizException("tag id不能为空", ReturnCode.INVALID_INPUT);
        }

        int i = tagService.deleteTagById(id);
        if (i == 1) {
            return "删除成功";
        } else {
            return "未找到对应的id";
        }
    }
}
