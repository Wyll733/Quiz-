import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizInocencia {

    private JFrame frame;
    private JPanel questionPanel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton, prevButton, answerButton, restartButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private final String[][] questions = {
            {"Qual o nome do protagonista masculino da história?", "Pereira", "Cirino", "Meyer", "Major", "2"},
            {"Onde se passa a história de Inocência?", "Rio de Janeiro", "São Paulo", "Sertão do Mato Grosso", "Minas Gerais", "3"},
            {"Qual o principal conflito da história?", "Uma disputa por terras", "Um amor proibido", "Uma investigação policial", "Uma aventura marítima", "2"},
            {"Quem é Myler em Inocência?", "Um historiador", "Um pesquisador", "Um fazendeiro", "Um doutor", "4"}
    };

    public QuizInocencia() {
        // Configuração da janela principal
        frame = new JFrame("QUIZ Inocência");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Painel de perguntas
        questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel("", JLabel.CENTER);
        questionPanel.add(questionLabel);

        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            questionPanel.add(options[i]);
        }

        frame.add(questionPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        prevButton = new JButton("Anterior");
        nextButton = new JButton("Próxima");
        answerButton = new JButton("Responder");
        restartButton = new JButton("Jogar de Novo");

        buttonPanel.add(prevButton);
        buttonPanel.add(answerButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(restartButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Configuração dos botões
        prevButton.addActionListener(e -> navigateQuestion(-1));
        nextButton.addActionListener(e -> navigateQuestion(1));
        answerButton.addActionListener(e -> checkAnswer());
        restartButton.addActionListener(e -> restartQuiz());

        // Inicializar o quiz
        loadQuestion();

        // Exibir a janela
        frame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.length) {
            String[] currentQuestion = questions[currentQuestionIndex];
            questionLabel.setText(currentQuestion[0]);
            for (int i = 0; i < options.length; i++) {
                options[i].setText(currentQuestion[i + 1]);
                options[i].setSelected(false);
            }
        }
    }

    private void navigateQuestion(int direction) {
        currentQuestionIndex += direction;
        if (currentQuestionIndex < 0) {
            currentQuestionIndex = 0;
        } else if (currentQuestionIndex >= questions.length) {
            currentQuestionIndex = questions.length - 1;
        }
        loadQuestion();
    }

    private void checkAnswer() {
        String correctAnswer = questions[currentQuestionIndex][5];
        int selectedOption = -1;
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                selectedOption = i + 1;
                break;
            }
        }

        if (selectedOption == Integer.parseInt(correctAnswer)) {
            score++;
        }

        JOptionPane.showMessageDialog(frame, "Resposta registrada!");
    }

    private void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        loadQuestion();
    }

    public static void main(String[] args) {
        new QuizInocencia();
    }
}
