package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Mock
    private Logger logger;

    @Test
    public void shouldValidateCard() {
        //Given
        TrelloCard card1 = new TrelloCard("card", "description", "234", "2");
        TrelloCard card2 = new TrelloCard("test card", "description", "234", "2");

        //When & Then
        trelloValidator.validateCard(card1);
        trelloValidator.validateCard(card2);

    }

    @Test
    public void shouldValidateBoards() {
        //Given
        TrelloBoard board1 = new TrelloBoard("1", "name1",
                List.of(new TrelloList("21", "name21", true)));
        TrelloBoard board2 = new TrelloBoard("2", "name2",
                List.of(new TrelloList("22", "name22", true)));
        List<TrelloBoard> boardList1 = List.of(board1, board2);

        TrelloBoard board3 = new TrelloBoard("3", "test",
                List.of(new TrelloList("23", "name23", true)));
        TrelloBoard board4 = new TrelloBoard("4", "name4",
                List.of(new TrelloList("24", "name24", true)));
        List<TrelloBoard> boardList2 = List.of(board3, board4);

        //When
        List<TrelloBoard> resultList1 = trelloValidator.validateTrelloBoards(boardList1);
        List<TrelloBoard> resultList2 = trelloValidator.validateTrelloBoards(boardList2);

        //Then
        assertEquals(2, resultList1.size());
        assertEquals(1, resultList2.size());
    }
}
