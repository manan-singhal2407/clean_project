`TODO`

1. Update rules if OfficialStats inside Fixtures->PrivateContest->Cricket/Football an only be written by role->admin.

Server Database:

AppData -> PrizeBreakup -> pushId -> 
[
    pushId(String),
    winningSpots(Int),
    Info[pushId -> [pushId(String), spots(1-10), percentagePrizePool(Double)]
]
AppData -> PrivateContest -> pushId -> 
[
    pushId,
    contestCode,
    dream11Url,
    status(live/completed/filledByUser/filledByOfficial/removedByUser/wrongCode)(Default value will be live, on match start set live/filledByUser value to completed)
]
AppData -> Teams -> pushId -> 
[
    pushId,
    teamName,
    teamCode,
    teamUrl
]

AppVersion -> 
[
    versionCode,
    versionName,
    forceUpdateCode,
    versionInfo
]

Fixtures -> Data -> Cricket/Football/Basketball -> fixtureId -> 
[
    fixtureId,
    createdAt,
    beginsAt,
    status(upcoming, live, completed, abandoned),
    pinMatch,
    leagueName,
    dream11Url,
    Teams[team1Data, team2Data] {Format: pushId_-_teamName_-_teamCode_-_teamUrl}
]
Fixtures -> PrivateContest -> Cricket/Football/Basketball -> pushId ->
[
    pushId,
    userId,
    addedAt,
    contestCode,
    status(filledByUser, filledByOfficial, notFilled, removedByUser, wrongCode),
    OfficialStats 
    {
        Condition: except status everything is fixed and check status based on updatedAt by user
        and official if it is removed by user any time don't show it if it is set to filled by
        user then don't show it.
        In case of wrongCode, remove contest to appear to users.
    }
    [
        updatedAt,
        status(filled, notFilled, wrongCode),
        spots,
        entry,
        winningSpots,
        prizeBreakup(pushId or default) {if default then retrieve data from other table or show error state},
        prizePool,
        multipleTeams(0, 1, 6, 11, 20),
        contestType(flexible, forced)
    ],
    UserStats
    [
        updatedAt,
        status(filled, notFilled, removed) {Default value = notFilled},
        spots,
        entry,
        winningSpots,
        prizeBreakup(pushId),
        prizePool,
        multipleTeams(0, 1, 6, 11, 20),
        contestType(flexible, forced)
    ]
]
Fixtures -> PrizeBreakup -> Cricket/Football/Basketball -> pushId ->
[
    pushId -> [pushId(String), spots(1-10), percentagePrizePool(Double)]
]

`If user is logged in`
Users -> Data -> userId ->
[
    role(String) {admin, user},
    userId,
    email,
    profileUrl,
    name,
    createdAt,
    Coins[total, spent]
]
Users -> History -> userId ->
[
    Profile[pushId -> [pushId, uploadedAt, profileUrl]],
    Coins[pushId -> [pushId, at, type(spent, earn), coins, through(video, interstitial)]],
    Name[pushId -> [pushId, updatedAt, name]]
    PrivateContest -> [pushId(Contest pre-defined id)(Boolean)]
    UserContest -> [FixtureId -> [PrivateContestId(true/false if user removed the contest)]]
]

Room Database:

Splash screen -> 
[
    PrivateContest(load last 10-20 contest id if user is logged in and append it to room database),
    PrizeBreakup (if no lottie present inside application)
]

User -> 
[
    emailId,
    profileUrl,
    name,
    totalCoins,
    spentCoins,
    createdAt
]

PrizeBreakup -> pushId -> 
[
    pushId(String),
    winningSpots(Int),
    Info[pushId -> [pushId(String), spots, percentagePrizePool(Double)]
]

PrivateContest -> pushId -> 
[
    pushId,
    contestCode
]

`If user is not logged in`
UserContest -> FixtureId
[
    PrivateContestId(true/false if user removed the contest)
]

Private contest stats:
Basic Info:

1. `contest size >= 2` and `entry >= 5`
2. If `contest size > 2` then show `Make contest flexible` option
3. If `contest size > 5` then `prize pool = 90% of total amount` else `prize pool = 95% of total amount`
4. If `contest size > 10` then show `Allow multiple teams per user` option
5. If `20000 >= contest size > 10000` then show Upto 20 teams or Single
6. If `10000 >= contest size > 100` then show Upto 11 teams or Single
7. If `100 >= contest size > 10` then show Upto 6 teams or Single

Prize Breakup Info:

1. Options: 1,2,3,4,5,7,10,15,25,50,100,250,500,1000,2000,5000
2. Show options which satisfies `spots/2`

a. Option 1:
1 -> 100%

b. Option 2:
1 -> 70% of total amount (70% of 95 = 67)
2 -> 30% of total amount (30% of 95 = 29)

and so on.