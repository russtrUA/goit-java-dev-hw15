package ua.goit.hw15springmvc.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.hw15springmvc.exception.NoteNotFoundException;
import ua.goit.hw15springmvc.model.Note;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final Map<Long, Note> content = new ConcurrentHashMap<>();
    private final Random random;
    private static final String EXCEPTION_MESSAGE = "Note with id %d not found.";

    public List<Note> listAll() {
        Collection<Note> notes = content.values();
        return new ArrayList<>(notes);
    }

    public Note add(Note note) {
        long id = random.nextLong();
        Note copyOfNote = Note.builder()
                .id(id)
                .content(note.getContent())
                .title(note.getTitle())
                .build();
        content.put(id, copyOfNote);
        return copyOfNote;
    }

    public void deleteById(long id) {
        if (!content.containsKey(id)) {
            throw new NoteNotFoundException(EXCEPTION_MESSAGE.formatted(id));
        }
        content.remove(id);
    }

    public void update(Note note) {
        if (!content.containsKey(note.getId())) {
            throw new NoteNotFoundException(EXCEPTION_MESSAGE.formatted(note.getId()));
        }
        Note newNote = Note.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
        content.put(note.getId(), newNote);
    }

    public Note getById(long id) {
        if (!content.containsKey(id)) {
            throw new NoteNotFoundException(EXCEPTION_MESSAGE.formatted(id));
        }
        Note noteFromMap = content.get(id);
        return Note.builder()
                .id(id)
                .title(noteFromMap.getTitle())
                .content(noteFromMap.getContent())
                .build();
    }

    @PostConstruct
    private void fillNotes() {
        add(Note.builder()
                .title("Meeting with the Marketing Team")
                .content("Discuss campaign goals for Q4, target audience adjustments, and new promotional strategies. Schedule follow-up meeting for feedback.")
                .build());
        add(Note.builder()
                .title("Project Deadline Reminder")
                .content("The deadline for the new website launch is October 15th. Ensure all testing and final tweaks are completed by October 10th for review.")
                .build());
        add(Note.builder()
                .title("Grocery List")
                .content("Milk, eggs, bread, coffee, apples, chicken breast, rice, and broccoli. Check for any additional pantry needs.")
                .build());
        add(Note.builder()
                .title("Book Recommendations")
                .content("Atomic Habits by James Clear, Sapiens by Yuval Noah Harari, Thinking, Fast and Slow by Daniel Kahneman, and Educated by Tara Westover.")
                .build());
        add(Note.builder()
                .title("Travel Plan for Conference")
                .content("Conference is on September 12-14 in Berlin. Book flights and accommodation. Prepare presentation materials and business cards.")
                .build());
        add(Note.builder()
                .title("Team Building Activities")
                .content("Plan for an outdoor team-building activity in the first week of October. Look into venues and possible group activities.")
                .build());
        add(Note.builder()
                .title("Quarterly Budget Review")
                .content("Review the financial statements and compare them to the budget. Identify areas with excessive spending or underspending.")
                .build());
        add(Note.builder()
                .title("UI Design Improvements")
                .content("Suggest improvements for the website's navigation menu and consider color adjustments for better accessibility.")
                .build());
        add(Note.builder()
                .title("Employee Feedback Survey")
                .content("Design a survey to gather feedback from employees on workplace satisfaction and areas for improvement.")
                .build());
        add(Note.builder()
                .title("Client Follow-Up Schedule")
                .content("Schedule follow-up calls for clients who attended the last product webinar. Aim to understand their needs and gather feedback.")
                .build());
        add(Note.builder()
                .title("Office Renovation Plan")
                .content("Prepare a renovation proposal for the common area. Include layout ideas, budget estimation, and project timeline.")
                .build());
        add(Note.builder()
                .title("Social Media Strategy")
                .content("Develop a content calendar for the next two months, focusing on increasing engagement and showcasing new products.")
                .build());
        add(Note.builder()
                .title("Product Launch Preparation")
                .content("Finalize logistics for the upcoming product launch, including venue booking, press releases, and media invites.")
                .build());
        add(Note.builder()
                .title("Security System Audit")
                .content("Conduct an internal audit of the security systems in place and recommend upgrades where necessary.")
                .build());
        add(Note.builder()
                .title("Monthly Team Meeting Agenda")
                .content("Prepare the agenda for the upcoming monthly meeting. Topics include project updates, team goals, and any challenges.")
                .build());
    }
}
