package de.telran.urlshortener.controller;

import de.telran.urlshortener.dto.ShortUrlRequest;
import de.telran.urlshortener.dto.ShortUrlResponse;
import de.telran.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlShortenerService service;

    @PostMapping("/createUrl")
    public ResponseEntity<ShortUrlResponse> createUrl(
            @RequestBody ShortUrlRequest request) {
        ShortUrlResponse response = service.createShortUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{key}")
    public RedirectView redirect(@PathVariable String key) {
        return service.getFullUrl(key);
    }
}
