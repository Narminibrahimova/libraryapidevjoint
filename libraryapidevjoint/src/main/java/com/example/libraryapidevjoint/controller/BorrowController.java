package com.example.libraryapidevjoint.controller;

import com.example.libraryapidevjoint.dto.request.BorrowBookRequestDto;
import com.example.libraryapidevjoint.dto.response.BorrowRecordResponseDto;
import com.example.libraryapidevjoint.security.CustomUserDetails;
import com.example.libraryapidevjoint.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Borrow Management", description = "APIs for borrowing and returning library books")
@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @Operation(
            summary = "Borrow a book",
            description = "Creates a borrow record for the authenticated user and book"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Book borrowed successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User or book not found"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Book is already borrowed"
            )
    })
    @PostMapping
    public ResponseEntity<BorrowRecordResponseDto> borrowBook(
            @Valid @RequestBody BorrowBookRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        BorrowRecordResponseDto response =
                borrowService.borrowBook(
                        request,
                        userDetails.getAppUser().getId()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(
            summary = "Return a borrowed book",
            description = "Returns a book and updates the borrow record status"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Book returned successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Borrow record not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Book has already been returned"
            )
    })
    @PutMapping("/return/{borrowRecordId}")
    public BorrowRecordResponseDto returnBook(
            @PathVariable Long borrowRecordId) {
        return borrowService.returnBook(borrowRecordId);
    }
}
