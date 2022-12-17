import axios from "axios";

class Event {
  constructor() {
    this.url = "http://localhost:8080/event";
  }

  addEvent(name, startDate, endDate) {
    return axios.post(this.url, { name, startDate, endDate });
  }

  getEventById(id) {
    return axios.get(this.url + "/" + id);
  }

  updateEvent(id, name, startDate, endDate) {
    return axios.put(this.url, { id, name, startDate, endDate });
  }

  deleteEventById(id) {
    return axios.delete(this.url + "/" + id);
  }

  getEventsWithDateGiven(date) {
    return axios.get(this.url, date);
  }
}

export default new Event();
