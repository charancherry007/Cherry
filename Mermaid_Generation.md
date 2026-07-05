# sop-workflow-mermaid

**Authors:** Your Team

**Description:** Generates a single high-level Mermaid workflow diagram from a Standard Operating Procedure (SOP). This skill analyzes business processes only and produces a Mermaid Flowchart representing the workflow. It does not generate implementation code or modify the repository except for the explicitly authorized output directory.

**Version:** 1.0

---

# Overview

This skill converts a Standard Operating Procedure (SOP) into a single Mermaid Flowchart that visualizes the complete business workflow.

The generated workflow should help users understand:

- Business process
- System interactions
- Decision points
- Alternate flows
- Failure paths
- Start and End states

The output is intended for technical documentation and workflow visualization.

---

# Scope

This skill ONLY performs the following:

- Read SOP documentation.
- Identify workflow sequence.
- Identify business actors.
- Identify participating systems.
- Identify business decisions.
- Identify alternate paths.
- Identify failure paths.
- Generate a single Mermaid Flowchart.
- Validate the generated workflow.
- Render the workflow as a PDF.
- Save the generated PDF to the authorized repository location.

This skill MUST NOT perform any additional analysis or generate any other Mermaid diagram type.

---

# Inputs

## Required

- SOP Document

## Optional

- Repository Documentation
- Knowledge Graph
- Existing Agent Skills

Reference documentation may only be used to clarify terminology.

The SOP is the single source of truth.

---

# Repository Permissions

This skill follows a **least-privilege execution model**.

Unless explicitly authorized below, the repository must be treated as **read-only**.

---

## Read Access

This skill MAY read:

- SOP documentation
- Repository documentation
- Knowledge Graph
- Existing Agent Skills
- Existing Markdown documentation
- Supporting reference documents

These resources are used only for workflow analysis.

---

## Write Access (Explicitly Authorized)

This skill is authorized to write **ONLY** to the following directory:

```text
src/mermaid-diagrams/
```

No other repository location may be:

- Created
- Modified
- Renamed
- Deleted
- Overwritten

This is the **only** filesystem modification permitted.

---

## Authorized Output

After generating the workflow:

1. Generate the Mermaid Flowchart.
2. Validate Mermaid syntax.
3. Render the workflow.
4. Export the rendered workflow as a PDF.
5. Save exactly one file:

```text
src/mermaid-diagrams/<SOP_NAME>.pdf
```

Where:

- `<SOP_NAME>` is derived from the SOP filename.
- Replace spaces with hyphens (`-`).
- Remove unsupported filename characters.
- Preserve readable naming where possible.

Examples:

```text
Customer-Onboarding.pdf
Portfolio-Analysis.pdf
Trade-Settlement.pdf
Claims-Processing.pdf
```

---

## Directory Creation

If

```text
src/mermaid-diagrams/
```

does not exist,

this skill MAY create **only** this directory.

No other directories may be created.

---

# Workflow Generation Requirements

Generate exactly one Mermaid Flowchart using:

```mermaid
flowchart TD
```

The workflow must include:

- Start node
- End node
- Sequential business process steps
- Decision nodes
- Success paths
- Failure paths
- Alternate paths
- External systems
- Business actors (when explicitly mentioned)

---

# Workflow Extraction Rules

Extract only information explicitly documented within the SOP.

Never invent:

- Business rules
- Missing workflow steps
- Systems
- Actors
- Integrations
- Decisions
- Validation rules

If information is absent from the SOP, omit it.

---

# Node Design Rules

Every workflow node MUST contain a meaningful business description.

Each node shall contain:

- Business action
- Optional business object
- Optional responsible actor

Examples:

✓ Receive Customer Request

✓ Validate Portfolio Data

✓ Submit Approval Request

✓ Review Compliance Status

✓ Update CRM

✓ Notify Customer

Avoid generic labels such as:

- Process
- Step
- Action
- Task
- Validation

Do not leave any node unlabeled.

Every node MUST contain visible text.
---

# Decision Rules

Every documented decision must contain all documented outcomes.

Include:

- Yes path
- No path

Where multiple outcomes exist, represent each documented outcome explicitly.

---

# External Systems

Represent each external system as an independent node.

Examples:

- CRM
- Salesforce
- ERP
- Notification Service
- Payment Gateway

Do not combine business actions with external systems.

---

# Flow Rules

The workflow must:

- Flow from top to bottom (`TD`)
- Preserve the SOP sequence exactly
- Minimize crossing connectors
- Keep related branches together
- Merge branches only when documented in the SOP
- Avoid unnecessary complexity

---

# Mermaid Rules

Generate **ONLY** Mermaid Flowcharts.

Do NOT generate:

- Sequence Diagrams
- Class Diagrams
- ER Diagrams
- State Diagrams
- Gantt Charts
- Mind Maps
- User Journey Diagrams
- Pie Charts
- Git Graphs

The generated Mermaid must be compatible with:

- GitHub
- VS Code Mermaid Preview
- Mermaid v11

---

# Workflow Validation Checklist

Before rendering the PDF, verify:

- Exactly one Start node exists.
- Exactly one End node exists.
- Every workflow step originates from the SOP.
- Every documented decision is represented.
- Every documented alternate path is included.
- Every documented failure path is included.
- External systems are represented.
- Business actors are represented where documented.
- No orphan nodes exist.
- Workflow order matches the SOP.
- Mermaid syntax is valid.
- Diagram renders successfully.

Only after **all validations succeed** may the PDF be generated.

---

# Strict Repository Protection

This skill MUST NOT:

### Modify Repository

- Source code
- Configuration files
- Build scripts
- CI/CD configuration
- Existing documentation
- Existing prompts
- Existing Agent Skills
- Existing Mermaid diagrams

### Modify Repository Structure

- Create folders outside the authorized directory
- Rename folders
- Delete folders
- Move files

### Generate Additional Artifacts

Do NOT generate:

- PNG
- SVG
- Mermaid (.mmd)
- Markdown (.md)
- JSON
- HTML
- DOCX
- TXT
- ZIP

unless explicitly requested by another skill.

---

# Failure Handling

If any validation or rendering step fails:

- Do not generate a partial PDF.
- Do not create temporary files.
- Do not write anywhere outside the authorized directory.
- Report the validation or rendering failure.
- Stop execution.

---

# Success Criteria

A successful execution produces **exactly one repository artifact**:

```text
src/
└── mermaid-diagrams/
    └── <SOP_NAME>.pdf
```

No additional files, directories, or repository modifications are permitted.

The generated workflow must:

- Accurately represent the SOP.
- Be business-focused.
- Be implementation-independent.
- Be visually readable.
- Be syntactically valid Mermaid.
- Render successfully as a PDF.
