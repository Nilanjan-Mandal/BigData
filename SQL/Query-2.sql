2. List down all Olympics games held so far. (Data issue at 1956-"Summer"-"Stockholm")

    select distinct oh.year,oh.season,oh.city
    from olympics_history oh
    order by year;
