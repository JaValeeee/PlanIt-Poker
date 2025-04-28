package four;

import javax.swing.*;
import java.awt.*;

/**
 * Stories organized in tabs.
 * The first tab contains the active stories, and the second one contains the completed stories.
 *
 * @author javiergs
 */
public class SouthPanel extends JPanel {
	private JTextArea completedStoriesArea;
	public SouthPanel() {
		setBackground(new Color(161, 190, 239));
		setLayout(new BorderLayout());
		JTabbedPane storyTabs = new JTabbedPane();
		
		// examples
		JTextArea activeStories = new JTextArea("As a player, I want to move Pac-man\n" +
			"As a player, I want to see an animated Pac-Man character");


		completedStoriesArea = new JTextArea(""); // Store the completed stories area
		completedStoriesArea.setEditable(false);

		storyTabs.addTab("Active Stories", new JScrollPane(activeStories));
		storyTabs.addTab("Completed Stories", new JScrollPane(completedStoriesArea));
		
		add(storyTabs, BorderLayout.CENTER);
	}

	// Add method to add a new completed story
	public void addCompletedStory(String story) {
		String existing = completedStoriesArea.getText();
		if (!existing.isEmpty()) {
			existing += "\n"; // add new line if not empty
		}
		completedStoriesArea.setText(existing + story);
	}

}


