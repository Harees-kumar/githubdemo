import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int borderWidth = 550;
    int borderHeight = 600; //50px for the text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton [][] board= new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns =0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(borderWidth , borderHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);//to deny resize
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//to exit
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.black);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TIC-TAC-TOE");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);// to move text to the top.

        boardPanel.setLayout(new GridLayout(3,3)); // to create grid.
        boardPanel.setBackground(Color.BLACK);
        frame.add(boardPanel);

        for(int r=0;r<3;r++){
            for(int c =0 ;c<3;c++){
                JButton tile= new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.BLACK);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font ("arial",Font.BOLD,120));
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer+"'s turn.");

                            }
    
                        }

                    }
                });

            }
        }
        

    }
    void checkWinner(){
        //horizontal
        for(int r =0;r<3;r++){
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() &&
            board[r][1].getText() == board[r][2].getText()){
                for(int i =0;i<3;i++){
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        // vertical
         for(int c =0;c<3;c++){
            if(board[0][c].getText() == "") continue;

            if(board[0][c].getText() == board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText()){
                for(int i =0;i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonal
        if(board[0][0].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][2].getText() &&
           board[0][0].getText() != ""){
            for(int i =0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
           }

           if(board[0][2].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][0].getText() &&
           board[0][2].getText() != ""){
           setWinner(board[0][2]);
           setWinner(board[1][1]);
           setWinner(board[2][0]);
            gameOver = true;
            return;
           }

           if(turns ==9){
            for (int i =0;i<3;i++){
                for(int j =0;j<3;j++){
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
           }

    }
    void setWinner(JButton tile){
        tile.setBackground(Color.gray);
        tile.setForeground(Color.GREEN);
        textLabel.setText(currentPlayer + "is the winner!");
    }

    void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Draw!");
    }
}
