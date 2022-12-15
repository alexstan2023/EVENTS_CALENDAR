class Day {
  getPreviousDay(date = new Date(), nrOfDaysInThePast) {
    const previous = new Date(date.getTime());
    previous.setDate(date.getDate() - nrOfDaysInThePast);
    return previous;
  }

  transformWeekDay(day) {
    switch (day) {
      case 0:
        return "Sunday";
      case 1:
        return "Monday";
      case 2:
        return "Tuesday";
      case 3:
        return "Wednesday";
      case 4:
        return "Thursday";
      case 5:
        return "Friday";
      case 6:
        return "Saturday";
    }
  }
}

export default new Day();
