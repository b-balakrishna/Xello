package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entities.Board;
import com.example.demo.entities.BoardMember;
import com.example.demo.entities.BoardVisibility;
import com.example.demo.entities.Card;
import com.example.demo.entities.Column;
import com.example.demo.entities.User;
import com.example.demo.repositories.BoardMemberRepository;
import com.example.demo.repositories.BoardRepository;
import com.example.demo.repositories.ColumnRepository;
import com.example.demo.repositories.UserRepository;

public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardMemberRepository boardMemberRepository;
    private final ColumnRepository columnRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository, BoardMemberRepository boardMemberRepository, ColumnRepository columnRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.boardMemberRepository = boardMemberRepository;
        this.columnRepository = columnRepository;
    }

    /**
     * Creates a new board with the specified name, owner (user), and visibility.
     *
     * @param userId          The ID of the user who will be the owner of the board.
     * @param boardName       The name of the board.
     * @param boardVisibility The visibility of the board.
     * @return The newly created Board object.
     * @throws RuntimeException if the user with the given ID does not exist.
     */
    public Board createBoard(Long userId, String boardName, BoardVisibility boardVisibility) {
        // Check if the user with the given id exists.
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        // Create a new Board object with the specified name, owner (user), and visibility.
        Board newBoard = new Board(boardName, user, boardVisibility);

        // Save the newly created Board object to the repository.
        Board board = boardRepository.save(newBoard);

        // Return the newly created Board.
        return board;
    }

    /**
     * Adds a user as a member to a board.
     *
     * @param boardId The ID of the board.
     * @param userId  The ID of the user to be added as a member.
     * @return The newly created BoardMember object.
     * @throws RuntimeException if the board or user with the given ID does not exist, or if the user is already a member.
     */
    public BoardMember addMemberToBoard(Long boardId, Long userId) {
        // Check if the board with the given id exists.
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board with an id " + boardId + " does not exist"));

        // Check if the user with the given id exists.
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        // Check if the user is already a member of the board.
        if (boardMemberRepository.exists(boardId, userId)) {
            throw new RuntimeException("User with an id "+ user.getId() + " is already a member of Board with an id " + board.getId());
        }

        // Create a new BoardMember object with the given board and user.
        BoardMember newBoardMember = new BoardMember(board, user);

        // Save the newly created BoardMember to the repository.
        BoardMember boardMember = boardMemberRepository.save(newBoardMember);

        // Return the newly created BoardMember.
        return boardMember;
    }

    /**
     * Removes a user from a board.
     *
     * @param boardId The ID of the board.
     * @param userId  The ID of the user to be removed.
     * @return True if the removal was successful, or false if the user was not a member of the board.
     * @throws RuntimeException if the board or user with the given ID does not exist.
     */
    public Boolean removeMemberFromBoard(Long boardId, Long userId) {
        // Check if the board with the given id exists.
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board with an id " + boardId + " does not exist"));

        // Check if the user with the given id exists.
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        // Check if the user is a member of the board.
        if (!boardMemberRepository.exists(boardId, userId)) {
            throw new RuntimeException("User with an id "+ user.getId() + " is not a member of Board with an id " + board.getId());
        }

        // If the user is a member, remove them from the board.
        boardMemberRepository.deleteById(boardId, userId);

        // Check if the user is a member of the board.
        if (!boardMemberRepository.exists(boardId, userId)) {
            return true; // Return true to indicate successful removal.
        }

        return false; 
    }

    /**
     * Adds a new column to the board with the specified name.
     *
     * @param boardId    The ID of the board where the column will be added.
     * @param columnName The name of the new column.
     * @return The newly created Column object.
     * @throws RuntimeException if the board with the given ID does not exist.
     */
    public Column addColumn(Long boardId, String columnName) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("Board with an id " + boardId + " does not exist"));
    
        Column column = new Column(columnName);
        column.setBoard(board);
    
        Column saved = columnRepository.save(column);
        board.addColumn(saved);
    
        return saved;
    }
    
    

    /**
     * Display the contents of a board, including its columns and cards.
     *
     * @param boardId The ID of the board to be displayed.
     * @throws RuntimeException if the board with the given ID does not exist.
     */

public void showBoard(Long boardId) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new RuntimeException("Board with an id " + boardId + " does not exist"));

    System.out.println("Board Name: " + board.getName());
    System.out.println("Visibility: " + board.getVisibility());

    // Get columns of the board, sorted by ID
    List<Column> columns = columnRepository.findAll().stream()
        .filter(c -> c.getBoard().getId().equals(boardId))
        .sorted((c1, c2) -> c1.getId().compareTo(c2.getId()))
        .collect(Collectors.toList());

    for (Column column : columns) {
        System.out.println("Column: " + column.getName());
        // Sort cards by ID for consistent order
        column.getCards().stream()
            .sorted((card1, card2) -> card1.getId().compareTo(card2.getId()))
            .forEach(card -> System.out.println("  Card: " + card.getTitle()));
    }
}


}