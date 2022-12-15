import "./App.css";
import { Routes, Route } from "react-router-dom";
import CalendarView from "./component/calendar/calendarView";
import EventView from "./component/event/eventView";

function App() {
  return (
    <Routes>
      <Route
        path="/calendar/view/:day/:month/:year"
        element={<CalendarView />}
      ></Route>
      <Route path="/event/view" element={<EventView />}></Route>
    </Routes>
  );
}

export default App;
