package com.forthset.search.rest;

import com.forthset.search.service.SearchService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Search")
@RequestMapping("/search")
public class SearchController {

  private SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }


}
