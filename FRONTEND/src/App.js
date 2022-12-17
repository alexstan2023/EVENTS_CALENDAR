import "./App.css";
import { Routes, Route, useNavigate, useParams } from "react-router-dom";
import CalendarView from "./component/calendar/calendarView";
import Day from "./utils/day";

function App() {
  const { day, month, year } = useParams();
  const navigate = useNavigate();

  const previousDay = Day.getPreviousDay(new Date(year, month, day), 1);
  const nextDay = Day.getPreviousDay(new Date(year, month, day), -1);

  return (
    <Routes>
      <Route
        path="/calendar/view/:day/:month/:year"
        element={
          <CalendarView
            onKeyDown={() => {navigate(
              `/calendar/view/${nextDay.getDate()}/${nextDay.getMonth()}/${nextDay.getFullYear()}`
            )}}
            onKeyUp={() => {navigate(
              `/calendar/view/${previousDay.getDate()}/${previousDay.getMonth()}/${previousDay.getFullYear()}`
            )}}
          />
        }
      ></Route>
    </Routes>
  );
}

export default App;
