import axios from "axios";

const BASE_URL = "http://localhost:8080/tasks";

export const getAllTasks = async () => {
  const res = await axios.get(BASE_URL);
  return res.data;
};

export const addTask = async (task) => {
  const res = await axios.post(BASE_URL, task);
  return res.data;
};

export const deleteTask = async (id) => {
  await axios.delete(`${BASE_URL}/${id}`);
};

export const renameTask = async (id, newName) => {
  const res = await axios.put(`${BASE_URL}/${id}`, { taskName: newName });
  return res.data;
};

export const updateTaskCompletion = async (id, isCompleted) => {
  const res = await axios.put(`${BASE_URL}/${id}/complete`, { isCompleted });
  return res.data;
};
