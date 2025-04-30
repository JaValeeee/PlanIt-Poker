package four;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Stories organized in tabs.
 * The first tab contains the active stories, and the second one contains the completed stories.
 *
 * After a vote, the completed story is updated and the story voted on is removed from active story
 *
 * Active stories is now based on user inputed stories
 *
 * Created a button to remove the top active story
 */
public class SouthPanel extends JPanel {
	private JTextArea completedStoriesArea;
	private JTextArea activeStoriesArea;
	private List<String> active = three.StoriesNanny.getActive();

	public SouthPanel() {
		setBackground(new Color(161, 190, 239));
		setLayout(new BorderLayout());

		JTabbedPane storyTabs = new JTabbedPane();

		StringBuilder actString = new StringBuilder();
		for (String s : active) {
			actString.append(s).append("\n");
		}
		activeStoriesArea = new JTextArea(String.valueOf(actString));
		activeStoriesArea.setEditable(false);

		completedStoriesArea = new JTextArea();
		completedStoriesArea.setEditable(false);

		storyTabs.addTab("Active Stories", new JScrollPane(activeStoriesArea));
		storyTabs.addTab("Completed Stories", new JScrollPane(completedStoriesArea));

		// Create the remove button
		JButton removeTopButton = new JButton("Remove Top Story");
		removeTopButton.addActionListener(e -> removeTopStory());

		// Create a panel for the button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(removeTopButton);

		// Add tabs and button to layout
		add(storyTabs, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public String topStory() {
		return active.isEmpty() ? "(No stories)" : active.getFirst();
	}

	public void addCompletedStory(String story) {
		String existing = completedStoriesArea.getText();
		StringBuilder fin = new StringBuilder();

		if (!active.isEmpty()) {
			active.removeFirst();
		}

		if (!existing.isEmpty()) {
			existing += "\n";
		}

		for (String s : active) {
			fin.append(s).append("\n");
		}

		completedStoriesArea.setText(existing + story);
		activeStoriesArea.setText(fin.toString());
	}

	// Helper to remove just the top story manually
	private void removeTopStory() {
		if (active.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No more active stories to remove.");
			return;
		}

		active.removeFirst();

		// Rebuild the activeStoriesArea text
		StringBuilder updated = new StringBuilder();
		for (String s : active) {
			updated.append(s).append("\n");
		}
		activeStoriesArea.setText(updated.toString());
	}
}
