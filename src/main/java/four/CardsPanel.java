package four;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays the cards used for estimating.
 *
 * @author javiergs
 */
public class CardsPanel extends JPanel {
	
	private static final String[] CARD_VALUES = {
		"0", "½", "1", "2", "3", "5", "8", "20", "40", "10", "0", "?"
	};


	public CardsPanel() {
		setLayout(new GridLayout(4, 3, 10, 10));
		for (String value : CARD_VALUES) {
			JButton card = new JButton(value);
			card.setBackground(new Color(172, 248, 199));
			card.setFont(new Font("SansSerif", Font.BOLD, 20));

			// Add action listener
			card.addActionListener(e -> handleCardClick(value));

			add(card);
		}
	}

	private void handleCardClick(String value) {
		// Dynamically find parent window
		Window window = SwingUtilities.getWindowAncestor(this);

		if (window instanceof JFrame) {
			JFrame parentFrame = (JFrame) window;

			String[] labels = { value };
			int[] votes = { 1 }; // Single 100% vote

			PieChartPanel pieChartPanel = new PieChartPanel(labels, votes);
			pieChartPanel.setStory("Story: Implement Login Feature");
			SouthPanel southPanel = new SouthPanel(); // Adding new completed stories
			southPanel.addCompletedStory("Story: Implement Login Feature"); // Add story to completed


			// Set layout if not already BorderLayout
			parentFrame.getContentPane().removeAll();
			parentFrame.setLayout(new BorderLayout());

			parentFrame.add(pieChartPanel, BorderLayout.CENTER);
			parentFrame.add(southPanel, BorderLayout.SOUTH);

			parentFrame.revalidate();
			parentFrame.repaint();
		}
	}
}

