import { isToday } from "date-fns";
import "./dayView.css";
import EventService from "../../service/eventService";

function DayView(props) {
  const cells = [];

  for (let i = 1; i <= 24; i++) {
    var nrOfEvents = EventService.getEventsWithDateGiven(
      new Date(props.day, props.month, props.year, i - 1, 0)
    ).length;
    if (nrOfEvents == 0) {
      cells.push(<td></td>);
    } else {
      cells.push(<td>{nrOfEvents}</td>);
    }
  }

  let isToday;

  if (
    props.day == new Date().getDate() &&
    props.month == new Date().getMonth() &&
    props.year == new Date().getFullYear()
  ) {
    isToday = true;
  } else {
    isToday = false;
  }

  return (
    <tr>
      <td
        className="left-column"
        style={{
          backgroundColor: isToday ? "yellow" : "aqua",
          position: "fixed",
          width: "348px",
        }}
      >
        <b>{props.weekday}</b>
        <br></br>
        <b>
          {props.day}-{props.month}-{props.year}
        </b>
      </td>
      {cells}
    </tr>
  );
}

export default DayView;
