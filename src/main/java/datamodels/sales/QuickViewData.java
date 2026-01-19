package datamodels.sales;

public class QuickViewData {

    private String status;
    private Task task;

    public String getStatus() {
        return status;
    }

    public Task getTask() {
        return task;
    }

    public static class Task {
        private String title;
        private String assignedTo;

        public String getTitle() {
            return title;
        }

        public String getAssignedTo() {
            return assignedTo;
        }
    }
}
