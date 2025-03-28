package com.example.mes.service;

import com.example.mes.domain.item.dto.ItemAddRequest;
import com.example.mes.domain.item.dto.ItemCreateRequest;
import com.example.mes.domain.item.dto.ItemDto;
import com.example.mes.domain.item.dto.ItemUpdateRequest;
import com.example.mes.domain.item.Item;
import com.example.mes.common.exception.MesAppException;
import com.example.mes.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.mes.common.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public ItemDto create(ItemCreateRequest itemCreateRequest) {
        return ItemDto.toItemDto(itemRepository.save(itemCreateRequest.toEntity()));
    }

    @Transactional(readOnly = true)
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream().map(ItemDto::toItemDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> findCompanyList() {
        List<String> distinctCompanyName = itemRepository.findCompanyList();
        log.info("companyList : {}", distinctCompanyName);
        return distinctCompanyName;
    }

    @Transactional
    public ItemDto addItem(ItemAddRequest request) {
        Item savedItem = itemRepository.save(Item.builder()
                .itemType(request.getItemType())
                .itemName(request.getItemName())
                .itemCode(request.getItemCode())
                .companyName(request.getCompanyName())
                .itemGroup(request.getItemGroup())
                .build());

        return ItemDto.toItemDto(savedItem);
    }

    @Transactional(readOnly = true)
    public List<ItemDto> findItemByCon(String companyName, String itemType) {
        return itemRepository.findCondition(companyName, itemType).stream().map(ItemDto::toItemDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemDto findDetailItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new MesAppException(ITEM_NOT_FOUND, ITEM_NOT_FOUND.getMessage()));
        log.info("COMPANYNAME:{}", item.getCompanyName());
        return ItemDto.toItemDto(item);
    }

    @Transactional
    public Long updateItem(Long itemId, ItemUpdateRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new MesAppException(ITEM_NOT_FOUND, ITEM_NOT_FOUND.getMessage()));
        Item updatedItem = Item.builder()
                .id(item.getId())
                .companyName(request.getCompanyName())
                .itemType(request.getItemType())
                .itemGroup(request.getItemGroup())
                .itemCode(request.getItemCode())
                .itemName(request.getItemName())
                .build();

        Item savedItem = itemRepository.save(updatedItem);

        return savedItem.getId();
    }

    @Transactional
    public Long deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
        return itemId;
    }

//    private static ItemDto getItemDto(Item savedItem) {
//        return ItemDto.builder()
//                .id(savedItem.getId())
//                .companyName(savedItem.getCompanyName())
//                .itemName(savedItem.getItemName())
//                .itemType(savedItem.getItemType())
//                .itemCode(savedItem.getItemCode())
//                .itemGroup(savedItem.getItemGroup())
//                .partNumber(savedItem.getPartNumber())
//                .receiptPaymentUnit(savedItem.getReceiptPaymentUnit())
//                .standard(savedItem.getStandard())
//                .purchaseUnit(savedItem.getPurchaseUnit())
//                .purchaseUnitQuantity(savedItem.getPurchaseUnitQuantity())
//                .requiredUnit(savedItem.getRequiredUnit())
//                .requiredUnitQuantity(savedItem.getRequiredUnitQuantity())
//                .yieldUnit(savedItem.getYieldUnit())
//                .yieldUnitQuantity(savedItem.getYieldUnitQuantity())
//                .build();
//    }
}
