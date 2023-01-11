package com.example.mes.controller;

import com.example.mes.domain.dto.ItemDto;
import com.example.mes.domain.request.ItemAddRequest;
import com.example.mes.domain.request.ItemCreateRequest;
import com.example.mes.domain.request.ItemUpdateRequest;
import com.example.mes.domain.response.ItemDeleteResponse;
import com.example.mes.domain.response.ItemUpdateResponse;
import com.example.mes.domain.response.Response;
import com.example.mes.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/items/detail")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemCreateRequest itemCreateRequest) {
        log.info("companyName:{}", itemCreateRequest.getCompanyName());
        ItemDto itemDto = itemService.create(itemCreateRequest);

        return ResponseEntity.ok().body(itemDto);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> findAllItem() {
        List<ItemDto> itemDtoList = itemService.findAll();
        return ResponseEntity.ok().body(itemDtoList);
    }

    @GetMapping("/items/company")
    public ResponseEntity<List<String>> findCompanyList() {
        List<String> companyResponseList = itemService.findCompanyList();
        return ResponseEntity.ok().body(companyResponseList);
    }

    @PostMapping("/items")
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemAddRequest request) {
        ItemDto itemDto = itemService.addItem(request);
        return ResponseEntity.ok().body(itemDto);
    }

    @GetMapping("/items/filter")
    public ResponseEntity<List<ItemDto>> findItemByCon(@RequestParam String companyName) {
        List<ItemDto> itemDtoList = itemService.findItemByCon(companyName);
        log.info("cnt : {}",itemDtoList.size());
        return ResponseEntity.ok().body(itemDtoList);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<Response<ItemDto>> findDetailItem(@PathVariable Long itemId) {
        ItemDto itemDto = itemService.findDetailItem(itemId);
        return ResponseEntity.ok().body(Response.success(itemDto));
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<Response<ItemUpdateResponse>> updateItem(@PathVariable Long itemId, @RequestBody ItemUpdateRequest request) {
        ItemDto itemDto = itemService.updateItem(itemId,request);
        return ResponseEntity.ok().body(Response.success(new ItemUpdateResponse("품목 수정 완료", itemId)));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Response<ItemDeleteResponse>> deleteItem(@PathVariable Long itemId) {
        Long deleteItemId = itemService.deleteItem(itemId);
        return ResponseEntity.ok().body(Response.success(new ItemDeleteResponse("품목 삭제 완료", deleteItemId)));
    }
}
