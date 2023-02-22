package com.example.mes.item.controller;

import com.example.mes.item.dto.ItemDto;
import com.example.mes.item.dto.ItemAddRequest;
import com.example.mes.item.dto.ItemCreateRequest;
import com.example.mes.item.dto.ItemUpdateRequest;
import com.example.mes.item.dto.ItemDeleteResponse;
import com.example.mes.item.dto.ItemUpdateResponse;
import com.example.mes.common.dto.Response;
import com.example.mes.item.service.ItemService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "품목 등록")
    @PostMapping("/detail")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemCreateRequest itemCreateRequest) {
        log.info("companyName:{}", itemCreateRequest.getCompanyName());
        ItemDto itemDto = itemService.create(itemCreateRequest);

        return ResponseEntity.ok().body(itemDto);
    }


    @ApiOperation(value = "품목 전체 조회")
    @GetMapping
    public ResponseEntity<List<ItemDto>> findAllItem() {
        List<ItemDto> itemDtoList = itemService.findAll();
        return ResponseEntity.ok().body(itemDtoList);
    }

    @ApiOperation(value = "회사 조회")
    @GetMapping("/company")
    public ResponseEntity<List<String>> findCompanyList() {
        List<String> companyResponseList = itemService.findCompanyList();
        return ResponseEntity.ok().body(companyResponseList);
    }

    @ApiOperation(value = "품목 등록")
    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemAddRequest request) {
        ItemDto itemDto = itemService.addItem(request);
        return ResponseEntity.ok().body(itemDto);
    }

    @ApiOperation(value = "품목 조건 검색")
    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> findItemByCon(@RequestParam(required = false) String companyName, @RequestParam(required = false) String itemType) {
        log.info("companyName : {}",companyName);
        log.info("itemType : {}",itemType);
        List<ItemDto> itemDtoList = itemService.findItemByCon(companyName, itemType);
        log.info("search cnt : {}", itemDtoList.size());
        return ResponseEntity.ok().body(itemDtoList);
    }

    @ApiOperation(value = "품목 상세 조회")
    @GetMapping("/{itemId}")
    public ResponseEntity<Response<ItemDto>> findDetailItem(@PathVariable Long itemId) {
        ItemDto itemDto = itemService.findDetailItem(itemId);
        return ResponseEntity.ok().body(Response.success(itemDto));
    }

    @ApiOperation(value = "품목 수정")
    @PutMapping("/{itemId}")
    public ResponseEntity<Response<ItemUpdateResponse>> updateItem(@PathVariable Long itemId, @RequestBody ItemUpdateRequest request) {
        ItemDto itemDto = itemService.updateItem(itemId, request);
        return ResponseEntity.ok().body(Response.success(new ItemUpdateResponse("품목 수정 완료", itemId)));
    }

    @ApiOperation(value = "품목 삭제")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Response<ItemDeleteResponse>> deleteItem(@PathVariable Long itemId) {
        Long deleteItemId = itemService.deleteItem(itemId);
        return ResponseEntity.ok().body(Response.success(new ItemDeleteResponse("품목 삭제 완료", deleteItemId)));
    }
}
