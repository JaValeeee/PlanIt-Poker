package four;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stories organized in tabs.
 * The first tab contains the active stories, and the second one contains the completed stories.
 *
 * After a vote, the completed story is updated and the story voted on is removed from active story
 *
 * Active stories is now based on user inputed stories
 *
 * @author javiergs
 */
public class SouthPanel extends JPanel {
	private JTextArea completedStoriesArea;
	private JTextArea activeStoriesArea;
	List<String> active = three.StoriesNanny.getActive();
	public SouthPanel() {
		setBackground(new Color(161, 190, 239));
		setLayout(new BorderLayout());
		JTabbedPane storyTabs = new JTabbedPane();
		

		StringBuilder actString = new StringBuilder();
		for (String s : active) {
			actString.append(s).append("\n");
		}
		activeStoriesArea = new JTextArea(String.valueOf(actString));


		completedStoriesArea = new JTextArea(""); // Store the completed stories area
		completedStoriesArea.setEditable(false);

		storyTabs.addTab("Active Stories", new JScrollPane(activeStoriesArea));
		storyTabs.addTab("Completed Stories", new JScrollPane(completedStoriesArea));
		
		add(storyTabs, BorderLayout.CENTER);
	}

	public String topStory(){
		return active.getFirst();
	}

	// Add method to add a new completed story
	public void addCompletedStory(String story) {
		String existing = completedStoriesArea.getText();
		StringBuilder fin = new StringBuilder();

		active.removeFirst();
		System.out.println(active);
		if (!existing.isEmpty()) {
			existing += "\n"; // add new line if not empty
		}

		for (String s : active) {
			fin.append(s).append("\n");
		}
		completedStoriesArea.setText(existing + story);
		activeStoriesArea.setText(String.valueOf(fin));
	}

}


