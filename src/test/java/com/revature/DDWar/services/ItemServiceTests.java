package com.revature.DDWar.services;

import com.revature.DDWar.entities.Item;
import com.revature.DDWar.repositories.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;

public class ItemServiceTests {

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository);
    }

    @Test
    public void getAllTest() {
        // Arrange
        List<Item> items = new ArrayList<>();
        items.add(new Item("name", "description", "type", 5));
        items.add(new Item("name", "description", "type", 5));
      
        when(itemRepository.findAll()).thenReturn(items);

        // Act
        List<Item> result = itemService.getItem();

        // Assert
        Assertions.assertEquals(items.size(), result.size());
        Assertions.assertEquals(items.get(0), result.get(0));
        Assertions.assertEquals(items.get(1), result.get(1));

        verify(itemRepository, times(1)).findAll();
        verifyNoMoreInteractions(itemRepository);
    }


      @Test
    public void testGetItemByName_ItemExists_ReturnsItem() {
        // Arrange
         List<Item> items = new ArrayList<>();
        items.add(new Item("name", "description", "type", 5));
        Optional<Item> optionalItem = Optional.of(items.get(0));

        when(itemRepository.findByItemName(  items.get(0).getItemName())).thenReturn(optionalItem);

        // Act
        Item result = itemService.getItemByName(items.get(0).getItemName());

        // Assert
        Assertions.assertEquals(items.get(0), result);

        verify(itemRepository, times(1)).findByItemName(items.get(0).getItemName());
        verifyNoMoreInteractions(itemRepository);
    }


    
}
