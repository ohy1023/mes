package com.example.mes.controller;

import com.example.mes.service.ItemService;
import com.example.mes.domain.item.dto.ItemDto;
import com.example.mes.domain.item.dto.ItemAddRequest;
import com.example.mes.domain.item.dto.ItemCreateRequest;
import com.example.mes.domain.item.dto.ItemUpdateRequest;
import com.example.mes.domain.item.dto.ItemDeleteResponse;
import com.example.mes.domain.item.dto.ItemUpdateResponse;
import com.example.mes.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/detail")
    public Response<ItemDto> createItem(@RequestBody ItemCreateRequest itemCreateRequest) {
        log.info("companyName:{}", itemCreateRequest.getCompanyName());
        ItemDto itemDto = itemService.create(itemCreateRequest);

        return Response.success(itemDto);
    }


    @GetMapping
    public Response<List<ItemDto>> findAllItem() {
        List<ItemDto> itemDtoList = itemService.findAll();
        return Response.success(itemDtoList);
    }

    @GetMapping("/company")
    public Response<List<String>> findCompanyList() {
        List<String> companyResponseList = itemService.findCompanyList();
        return Response.success(companyResponseList);
    }

    @PostMapping
    public Response<ItemDto> addItem(@RequestBody ItemAddRequest request) {
        ItemDto itemDto = itemService.addItem(request);
        return Response.success(itemDto);
    }

    @GetMapping("/search")
    public Response<List<ItemDto>> findItemByCon(@RequestParam(required = false) String companyName, @RequestParam(required = false) String itemType) {
        log.info("companyName : {}", companyName);
        log.info("itemType : {}", itemType);
        List<ItemDto> itemDtoList = itemService.findItemByCon(companyName, itemType);
        log.info("search cnt : {}", itemDtoList.size());
        return Response.success(itemDtoList);
    }

    @GetMapping("/{itemId}")
    public Response<ItemDto> findDetailItem(@PathVariable Long itemId) {
        ItemDto itemDto = itemService.findDetailItem(itemId);
        return Response.success(itemDto);
    }

    @PutMapping("/{itemId}")
    public Response<ItemUpdateResponse> updateItem(@PathVariable Long itemId, @RequestBody ItemUpdateRequest request) {
        Long updatedItemId = itemService.updateItem(itemId, request);
        return Response.success(new ItemUpdateResponse("품목 수정 완료", updatedItemId));
    }

    @DeleteMapping("/{itemId}")
    public Response<ItemDeleteResponse> deleteItem(@PathVariable Long itemId) {
        Long deleteItemId = itemService.deleteItem(itemId);
        return Response.success(new ItemDeleteResponse("품목 삭제 완료", deleteItemId));
    }
}
