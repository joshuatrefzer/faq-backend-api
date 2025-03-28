package com.example.faq_backend_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.faq_backend_api.api.interfaces.TagRepository;
import com.example.faq_backend_api.api.model.Tag;
import com.example.faq_backend_api.service.TagService;


@ExtendWith(MockitoExtension.class)
public class TagServiceTest {
    @Mock
    private TagRepository tagRepository; 

    @InjectMocks
    private TagService tagService; 

    private Tag tag1;
    private Tag tag2;

    @BeforeEach
    void setUp() {
        tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Spring");

        tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Java");
    }

    @Test
    void testCreateTag() {
        when(tagRepository.save(any(Tag.class))).thenReturn(tag1);

        Tag createdTag = tagService.createTag("Spring");

        assertNotNull(createdTag);
        assertEquals("Spring", createdTag.getName());
        verify(tagRepository, times(1)).save(any(Tag.class));
    }

    @Test
    void testGetAllTags() {
        when(tagRepository.findAll()).thenReturn(Arrays.asList(tag1, tag2));

        List<Tag> tags = tagService.getAllTags();

        assertEquals(2, tags.size());
        assertEquals("Spring", tags.get(0).getName());
        assertEquals("Java", tags.get(1).getName());
        verify(tagRepository, times(1)).findAll();
    }

    @Test
    void testGetTagByName_Found() {
        when(tagRepository.findByName("Spring")).thenReturn(tag1);

        Optional<Tag> result = tagService.getTagByName("Spring");

        assertTrue(result.isPresent());
        assertEquals("Spring", result.get().getName());
        verify(tagRepository, times(1)).findByName("Spring");
    }

    @Test
    void testGetTagByName_NotFound() {
        when(tagRepository.findByName("Unknown")).thenReturn(null);

        Optional<Tag> result = tagService.getTagByName("Unknown");

        assertFalse(result.isPresent());
        verify(tagRepository, times(1)).findByName("Unknown");
    }
    
}
