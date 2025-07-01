import React, { useEffect, useState } from "react";
import {
  getAllTasks,
  addTask,
  deleteTask,
  renameTask,
  updateTaskCompletion,
} from "./api";

function App() {
  const [tasks, setTasks] = useState([]);
  const [newTaskName, setNewTaskName] = useState("");
  const [newDeadline, setNewDeadline] = useState("");
  const [taskImportance, setTaskImportance] = useState(1);
  const [renameText, setRenameText] = useState({});

  const loadTasks = () => {
    getAllTasks().then(setTasks);
  };

  useEffect(() => {
    loadTasks();
  }, []);

  const handleAdd = () => {
    if (newTaskName.trim()) {
      const isoDeadline = newDeadline ? `${newDeadline}T00:00:00` : null;

      addTask({
        taskName: newTaskName,
        deadline: isoDeadline,
        taskImportance,
        isCompleted: false,
      }).then(() => {
        setNewTaskName("");
        setNewDeadline("");
        setTaskImportance(1);
        loadTasks();
      });
    }
  };

  const handleRename = (id) => {
    if (renameText[id]?.trim()) {
      renameTask(id, renameText[id]).then(loadTasks);
    }
  };

  const handleDelete = (id) => {
    deleteTask(id).then(loadTasks);
  };

  return (
    <div style={{ padding: "2rem", fontFamily: "sans-serif" }}>
      <h1>Task Manager</h1>
      <p style={{ marginBottom: "0.5rem", fontWeight: "bold" }}>
        Create a task here:
      </p>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="text"
          placeholder="New task name"
          value={newTaskName}
          onChange={(e) => setNewTaskName(e.target.value)}
          style={{ marginRight: "0.5rem", padding: "0.5rem" }}
        />
        <input
          type="date"
          value={newDeadline}
          onChange={(e) => setNewDeadline(e.target.value)}
          style={{ marginRight: "0.5rem", padding: "0.5rem" }}
        />
        <label style={{ marginRight: "0.5rem" }}>
          Importance:
          <input
            type="number"
            min="1"
            max="10"
            value={taskImportance}
            onChange={(e) => setTaskImportance(Number(e.target.value))}
            style={{ marginLeft: "0.5rem", padding: "0.5rem", width: "4rem" }}
          />
        </label>
        <button
          onClick={handleAdd}
          style={{ padding: "0.5rem 1rem", borderRadius: "6px" }}
        >
          Add Task
        </button>
      </div>

      <ul style={{ listStyle: "none", paddingLeft: 0 }}>
        {tasks.map((task) => (
          <li
            key={task.id}
            style={{
              marginBottom: "1rem",
              background: "#f4f4f4",
              padding: "1rem",
              borderRadius: "10px",
            }}
          >
            <div>
              <strong>{task.taskName}</strong>
              {task.deadline && !isNaN(new Date(task.deadline)) && (
                <span style={{ marginLeft: "0.5rem", color: "#888" }}>
                  (Due: {new Date(task.deadline).toLocaleDateString()})
                </span>
              )}
              <div style={{ marginTop: "0.25rem", color: "#555" }}>
                Importance: {task.taskImportance}
                <label style={{ marginLeft: "1rem" }}>
                  <input
                    type="checkbox"
                    checked={task.isCompleted}
                    onChange={(e) =>
                      updateTaskCompletion(task.id, e.target.checked).then(
                        loadTasks
                      )
                    }
                    style={{ marginRight: "0.25rem" }}
                  />
                  Completed
                </label>
              </div>
            </div>

            <input
              style={{
                marginTop: "0.5rem",
                marginRight: "0.5rem",
                padding: "0.4rem",
              }}
              placeholder="New name"
              value={renameText[task.id] || ""}
              onChange={(e) =>
                setRenameText({ ...renameText, [task.id]: e.target.value })
              }
            />
            <button
              onClick={() => handleRename(task.id)}
              style={{ marginRight: "0.5rem", borderRadius: "6px" }}
            >
              Rename
            </button>
            <button
              onClick={() => handleDelete(task.id)}
              style={{ borderRadius: "6px" }}
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
