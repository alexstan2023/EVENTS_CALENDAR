import DayView from "../day/dayView";
import "./calendarView.css";

function CalendarView() {
  const hour = [];
  const days = [];
  const weekdays = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday",
  ];

  hour.push(<th className="top-left-corner"></th>);
  hour.push(<th className="top-row">12AM</th>);
  for (let i = 1; i <= 11; i++) hour.push(<th className="top-row">{i}AM</th>);
  hour.push(<th className="top-row">12PM</th>);
  for (let i = 1; i <= 11; i++) {
    hour.push(<th className="top-row">{i}PM</th>);
  }

  for (let i = 1; i <= 7; i++) {
    days.push(<DayView weekday={weekdays[i - 1]}></DayView>);
  }

  return (
    <table>
      <tr>{hour}</tr>
      {days}
    </table>
  );
}

export default CalendarView;
