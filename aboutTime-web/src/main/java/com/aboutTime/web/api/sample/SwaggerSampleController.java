package com.aboutTime.web.api.sample;

import com.aboutTime.web.api.docs.swagger.SwaggerSampleContollerDocs;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "SwaggerSampleController", description = "Swagger API Sample 목록")
@RequestMapping("/sample/docs")
public class SwaggerSampleController implements SwaggerSampleContollerDocs {
    @GetMapping("/get")
    public String ApiGetSample() {
        return "Hello this is Get method Sample";
    }

    @PostMapping("/post")
    public String ApiPostSample() {
        return "Hello this is Post method Sample";
    }

    @PutMapping ("/put")
    public String ApiPutSample() {
        return "Hello this is Put method Sample";
    }

    @DeleteMapping("/delete")
    public String ApiDeleteSample() {
        return "Hello this is Delete method Sample";
    }
}
