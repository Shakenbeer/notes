# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

Build and install debug APK:
```
./gradlew installDebug
```

Run unit tests:
```
./gradlew testDebugUnitTest
```

Run a single test class:
```
./gradlew testDebugUnitTest --tests "com.shakenbeer.notes.domain.NodeLabTest"
```

Run instrumented tests (requires connected device/emulator):
```
./gradlew connectedDebugAndroidTest
```

## Architecture

This is an Android notes app using Jetpack Compose, Room, and a manual DI pattern.

**Layer structure:**

- `domain/` — pure Kotlin models (`Note`, `NodeContent`, `Reminder`) and interfaces (`NoteRepository`, `NotificationService`). `NoteLab` is the domain facade that coordinates saving notes with notification scheduling.
- `data/` — Room implementation. `NoteRepositoryImpl` wraps `NoteDao`. `Converters` handles `UUID` ↔ `String` and `Instant` ↔ `Long` for Room.
- `notification/` — Android-side notification impl. `AndroidNotificationService` uses `AlarmManager` for exact alarms; `NotificationReceiver` is a `BroadcastReceiver` that fires the system notification.
- `ui/` — Compose screens. Each screen has a `*Destination` composable (entry point, owns the ViewModel) and a stateless `*Screen` composable. UI state is a `StateFlow` exposed from the ViewModel.

**Dependency injection:** Manual via `Injector` singleton (no Hilt/Dagger). `Injector.init(context)` is called in `NotesApplication`. ViewModels pull from `Injector.noteLab` inside their `Factory`.

**Navigation:** Not yet wired — `NoteListDestination` is set as the root in `MainActivity` directly; `NoteDestination` exists but is not reachable from the list screen yet.

**`Note` entity:** Uses `@Embedded` for `NodeContent` (columns: `title`, `body`) and `@Embedded(prefix = "reminder_")` for `Reminder` (column: `reminder_timestamp`). The primary key is a `UUID` stored as a `String`.

**Reminder flow:** When a note is saved via `NoteLab.save()`, if a `Reminder` is present, `AndroidNotificationService.scheduleNotification()` sets an exact alarm. On API 31+, it checks `canScheduleExactAlarms()` first (requires the `SCHEDULE_EXACT_ALARM` permission).
