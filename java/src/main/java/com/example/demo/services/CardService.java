package com.example.demo.services;

import com.example.demo.entities.Card;
import com.example.demo.entities.Comment;
import com.example.demo.entities.User;
import com.example.demo.models.CardUpdateDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.CommentRepository;

public class CardService {

     private final CardRepository cardRepository;
     private final UserRepository userRepository;
     private final CommentRepository commentRepository;

     public CardService(CardRepository cardRepository, UserRepository userRepository,
               CommentRepository commentRepository) {
          this.cardRepository = cardRepository;
          this.userRepository = userRepository;
          this.commentRepository = commentRepository;
     }

     /**
      * Updates the properties of a card (e.g., title and description) based on the provided DTO.
      *
      * @param cardId The ID of the card to be updated.
      * @param cardUpdateDTO The DTO containing updated card information.
      * @return The updated Card object.
      * @throws RuntimeException if the card with the given ID does not exist.
      */
      public Card updateCard(Long cardId, CardUpdateDTO dto) {
          Card card = cardRepository.findById(cardId)
              .orElseThrow(() -> new RuntimeException("Card with an id " + cardId + " does not exist"));
      
          if (dto.getNewTitle() != null && !"null".equalsIgnoreCase(dto.getNewTitle())) {
              card.setTitle(dto.getNewTitle());
          }
      
          if (dto.getNewDescription() != null && !"null".equalsIgnoreCase(dto.getNewDescription())) {
              card.setDescription(dto.getNewDescription());
          }
      
          return cardRepository.save(card);
      }
      

     /**
      * Adds a user as a member to a card.
      *
      * @param cardId The ID of the card to which the user will be added as a member.
      * @param userId The ID of the user to be added as a member.
      * @return The updated Card object.
      * @throws RuntimeException if the card or user with the given ID does not exist or if the user
      *         is already a member of the card.
      */
      public Card addMemberToCard(Long cardId, Long userId) {
          Card card = cardRepository.findById(cardId)
              .orElseThrow(() -> new RuntimeException("Card with an id " + cardId + " does not exist"));

          User user = userRepository.findById(userId)
              .orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));
      
          if (card.getMembers().contains(user)) {
              throw new RuntimeException(
                  "User with an id " + userId + " is already a member of the card with an id " + cardId
              );
          }
      
          card.addMember(user);
      
          return cardRepository.save(card);
      }
      

     /**
      * Removes a user from the members of a card.
      *
      * @param cardId The ID of the card from which the user will be removed.
      * @param userId The ID of the user to be removed from the card's members.
      * @return true if the user is successfully removed, false otherwise.
      * @throws RuntimeException if the card or user with the given ID does not exist or if the user
      *         is not a member of the card.
      */
      public Boolean removeMemberFromCard(Long cardId, Long userId) {
          // Check if card exists
          Card card = cardRepository.findById(cardId)
                  .orElseThrow(() -> new RuntimeException("Card with an id " + cardId + " does not exist"));
      
          // Check if user exists
          User user = userRepository.findById(userId)
                  .orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));
      
          // Check membership
          if (!card.getMembers().contains(user)) {
              throw new RuntimeException("User with an id " + userId + " is not a member of the card with an id " + cardId);
          }
      
          // Remove and save
          card.removeMember(user);
          cardRepository.save(card);
          return true;
      }      


     /**
      * Adds a comment to a card.
      *
      * @param userId The ID of the user adding the comment.
      * @param cardId The ID of the card to which the comment will be added.
      * @param commentText The text of the comment.
      * @return The newly created Comment object.
      * @throws RuntimeException if the card or user with the given ID does not exist.
      */
      public Comment addCommentToCard(Long userId, Long cardId, String text) {
          User user = userRepository.findById(userId)
              .orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));
      
          Card card = cardRepository.findById(cardId)
              .orElseThrow(() -> new RuntimeException("Card with an id " + cardId + " does not exist"));
      
          Comment comment = new Comment(card, user, text);
          Comment savedComment = commentRepository.save(comment);
          card.addComment(savedComment);
          cardRepository.save(card);
          return savedComment;
      }
      
      
}

